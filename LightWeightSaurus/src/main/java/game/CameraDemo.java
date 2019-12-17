package game;

import engine.GameItem;
import engine.IGameLogic;
import engine.Input;
import engine.Window;
import graphics.Camera;
import graphics.Mesh;
import graphics.Renderer;
import graphics.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;
import utils.OBJLoader;

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

        Texture texture = new Texture("/textures/grassblock.png");
        Mesh mesh = OBJLoader.loadMesh("/models/cube.obj");
        mesh.setTexture(texture);

        GameItem gameItem1 = new GameItem(mesh);
        gameItem1.setScale(0.25f);
        gameItem1.setPosition(0, 0, -2);

        GameItem gameItem2 = new GameItem(mesh);
        gameItem2.setScale(0.25f);
        gameItem2.setPosition(0.5f, 0.5f, -2);

        GameItem gameItem3 = new GameItem(mesh);
        gameItem3.setScale(0.25f);
        gameItem3.setPosition(0, 0, -2.5f);

        GameItem gameItem4 = new GameItem(mesh);
        gameItem4.setScale(0.25f);

        gameItem4.setPosition(0.5f, 0, -2.5f);
        gameItems = new GameItem[]{gameItem1, gameItem2, gameItem3, gameItem4};

    }

    @Override
    public void update(float interval) {
        Vector3f cameraMove = new Vector3f(0, 0, 0);
        if (Input.isKeyDown(GLFW_KEY_W)) {
            cameraMove.z = 1;
        } else if (Input.isKeyDown(GLFW_KEY_S)) {
            cameraMove.z = -1;
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

        Vector2f cameraRotate = new Vector2f(0, 0);

        if (Input.isKeyDown(GLFW_KEY_UP)) {
            cameraRotate.x = -1;
        } else if (Input.isKeyDown(GLFW_KEY_DOWN)) {
            cameraRotate.x = 1;
        }
        if (Input.isKeyDown(GLFW_KEY_LEFT)) {
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
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().destroy();
        }
    }
}
