package game;

import engine.IGameLogic;
import engine.Input;
import engine.Window;
import graphics.Camera;
import graphics.Mesh;
import graphics.Renderer;
import graphics.Texture;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class RollingCubeDemo implements IGameLogic {

    private static final float MOUSE_SENSITIVITY = 0.2f;

    private final Vector3f cameraInc;

    private final Renderer renderer;

    private final Camera camera;

    private GameItem[] gameItems;

    private static final float CAMERA_POS_STEP = 0.05f;


    public RollingCubeDemo() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector3f(0, 0, 0);
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        float[] vertices = new float[]{
            // V0
            -0.5f, 0.5f, 0.5f,
            // V1
            -0.5f, -0.5f, 0.5f,
            // V2
            0.5f, -0.5f, 0.5f,
            // V3
            0.5f, 0.5f, 0.5f,
            // V4
            -0.5f, 0.5f, -0.5f,
            // V5
            0.5f, 0.5f, -0.5f,
            // V6
            -0.5f, -0.5f, -0.5f,
            // V7
            0.5f, -0.5f, -0.5f,

            // For text coords in top face
            // V8: V4 repeated
            -0.5f, 0.5f, -0.5f,
            // V9: V5 repeated
            0.5f, 0.5f, -0.5f,
            // V10: V0 repeated
            -0.5f, 0.5f, 0.5f,
            // V11: V3 repeated
            0.5f, 0.5f, 0.5f,

            // For text coords in right face
            // V12: V3 repeated
            0.5f, 0.5f, 0.5f,
            // V13: V2 repeated
            0.5f, -0.5f, 0.5f,

            // For text coords in left face
            // V14: V0 repeated
            -0.5f, 0.5f, 0.5f,
            // V15: V1 repeated
            -0.5f, -0.5f, 0.5f,

            // For text coords in bottom face
            // V16: V6 repeated
            -0.5f, -0.5f, -0.5f,
            // V17: V7 repeated
            0.5f, -0.5f, -0.5f,
            // V18: V1 repeated
            -0.5f, -0.5f, 0.5f,
            // V19: V2 repeated
            0.5f, -0.5f, 0.5f,
        };
        float[] textCoords = new float[]{
            0.0f, 0.0f,
            0.0f, 0.5f,
            0.5f, 0.5f,
            0.5f, 0.0f,

            0.0f, 0.0f,
            0.5f, 0.0f,
            0.0f, 0.5f,
            0.5f, 0.5f,

            // For text coords in top face
            0.0f, 0.5f,
            0.5f, 0.5f,
            0.0f, 1.0f,
            0.5f, 1.0f,

            // For text coords in right face
            0.0f, 0.0f,
            0.0f, 0.5f,

            // For text coords in left face
            0.5f, 0.0f,
            0.5f, 0.5f,

            // For text coords in bottom face
            0.5f, 0.0f,
            1.0f, 0.0f,
            0.5f, 0.5f,
            1.0f, 0.5f,
        };
        int[] indices = new int[]{
            // Front face
            0, 1, 3, 3, 1, 2,
            // Top Face
            8, 10, 11, 9, 8, 11,
            // Right face
            12, 13, 7, 5, 12, 7,
            // Left face
            14, 15, 6, 4, 14, 6,
            // Bottom face
            16, 18, 19, 17, 16, 19,
            // Back face
            4, 6, 7, 5, 4, 7,
        };

        Texture texture = new Texture("/textures/grassblock.png");
        Mesh mesh = new Mesh(vertices, textCoords, indices, texture);
        GameItem gameItem = new GameItem(mesh);
        gameItem.setPosition(0, 0, -2);
        gameItems = new GameItem[]{gameItem};
    }

    @Override
    public void update(float interval) {
        int displyInc = 0;
        int displxInc = 0;
        int displzInc = 0;
        int scaleInc = 0;
        if (Input.isKeyDown(GLFW_KEY_UP)) {
            displyInc = 1;
        } else if (Input.isKeyDown(GLFW_KEY_DOWN)) {
            displyInc = -1;
        } else if (Input.isKeyDown(GLFW_KEY_LEFT)) {
            displxInc = -1;
        } else if (Input.isKeyDown(GLFW_KEY_RIGHT)) {
            displxInc = 1;
        } else if (Input.isKeyDown(GLFW_KEY_A)) {
            displzInc = -1;
        } else if (Input.isKeyDown(GLFW_KEY_Q)) {
            displzInc = 1;
        } else if (Input.isKeyDown(GLFW_KEY_Z)) {
            scaleInc = -1;
        } else if (Input.isKeyDown(GLFW_KEY_X)) {
            scaleInc = 1;
        }

        for (GameItem gameItem : gameItems) {
            // Update position
            org.joml.Vector3f itemPos = gameItem.getPosition();
            float posX = itemPos.x + displxInc * 0.05f;
            float posY = itemPos.y + displyInc * 0.05f;
            float posZ = itemPos.z + displzInc * 0.05f;
            gameItem.setPosition(posX, posY, posZ);

            // Update scale
            float scale = gameItem.getScale();
            scale += scaleInc * 0.05f;
            if (scale < 0) {
                scale = 0;
            }
            gameItem.setScale(scale);

            // Update rotation angle
            float rotation = gameItem.getRotation().z + 1.5f;
            if (rotation > 360) {
                rotation = 0;
            }
            gameItem.setRotation(rotation, rotation, rotation);
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, gameItems);
    }

    @Override
    public void destroy() {
        renderer.destroy();
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().destroy();
        }
    }
}
