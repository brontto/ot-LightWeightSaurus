package game;

import engine.GameItem;
import engine.IGameLogic;
import engine.Input;
import engine.Window;
import graphics.Camera;
import graphics.Mesh;
import graphics.Renderer;
import graphics.Texture;
import org.joml.Vector3f;
import utils.OBJLoader;

import static org.lwjgl.glfw.GLFW.*;

public class RollingCubeDemo implements IGameLogic {

    private final Vector3f cameraInc;

    private final Renderer renderer;

    private final Camera camera;

    private GameItem[] gameItems;

    public RollingCubeDemo() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector3f(0, 0, 0);
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);

        Texture texture = new Texture("/textures/grassblock.png");
        Mesh mesh = OBJLoader.loadMesh("/models/cube.obj");
        mesh.setTexture(texture);
        GameItem gameItem = new GameItem(mesh);
        gameItem.setScale(0.5f);
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
