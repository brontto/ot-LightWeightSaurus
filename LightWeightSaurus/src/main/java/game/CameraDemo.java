package game;

import engine.IGameLogic;
import engine.Input;
import engine.Window;
import graphics.Camera;
import graphics.Mesh;
import graphics.Renderer;
import graphics.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_X;

public class CameraDemo implements IGameLogic {

    private final Renderer renderer;

    private final Camera camera;

    private GameItem[] gameItems;

    public CameraDemo() {
        this.renderer = new Renderer();
        this.camera = new Camera();
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
        GameItem gameItem1 = new GameItem(mesh);
        gameItem1.setScale(0.5f);
        gameItem1.setPosition(0, 0, -2);

        GameItem gameItem2 = new GameItem(mesh);
        gameItem2.setScale(0.5f);
        gameItem2.setPosition(0.5f, 0.5f, -2);

        GameItem gameItem3 = new GameItem(mesh);
        gameItem3.setScale(0.5f);
        gameItem3.setPosition(0, 0, -2.5f);

        GameItem gameItem4 = new GameItem(mesh);
        gameItem4.setScale(0.5f);

        gameItem4.setPosition(0.5f, 0, -2.5f);
        gameItems = new GameItem[]{gameItem1, gameItem2, gameItem3, gameItem4};

    }

    @Override
    public void update(float interval) {
        Vector3f cameraMove = new Vector3f(0, 0, 0);
        if (Input.isKeyDown(GLFW_KEY_W)) {
            cameraMove.z = -1;
        } else if (Input.isKeyDown(GLFW_KEY_S)) {
            cameraMove.z = 1;
        }
        if (Input.isKeyDown(GLFW_KEY_A)) {
            cameraMove.x = -1;
        } else if (Input.isKeyDown(GLFW_KEY_D)) {
            cameraMove.x = 1;
        }
        if (Input.isKeyDown(GLFW_KEY_Z)) {
            cameraMove.y = -1;
        } else if (Input.isKeyDown(GLFW_KEY_X)) {
            cameraMove.y = 1;
        }

        // Update camera position
        camera.movePosition(cameraMove.x * 0.05f,
                cameraMove.y * 0.05f,
                cameraMove.z * 0.05f);

        // Update camera based on mouse

        Vector2f cameraRotate = new Vector2f(0,0);

        if (Input.isKeyDown(GLFW_KEY_UP)) {
            cameraRotate.x = -1;
        } else if (Input.isKeyDown(GLFW_KEY_DOWN)) {
            cameraRotate.x = 1;
        }
        if (Input.isKeyDown(GLFW_KEY_LEFT)){
            cameraRotate.y = -1;
        } else if (Input.isKeyDown(GLFW_KEY_RIGHT)) {
            cameraRotate.y = 1;
        }

        camera.moveRotation(cameraRotate.x * 0.5f, cameraRotate.y * 0.5f, 0);

    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, gameItems);
    }

    @Override
    public void destroy() {
        renderer.destroy();
        for (GameItem gameItem : gameItems){
            gameItem.getMesh().destroy();
        }
    }
}
