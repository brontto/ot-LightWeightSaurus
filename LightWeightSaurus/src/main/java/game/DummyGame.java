package game;

import engine.IGameLogic;
import engine.Input;
import engine.Window;
import graphics.Mesh;
import graphics.Renderer;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class DummyGame implements IGameLogic {

    private final Renderer renderer;

    private Mesh mesh;

    private GameItem[] gameItems;

    public DummyGame() {
        this.renderer = new Renderer();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        Vector3f[] vertices = new Vector3f[]{
            new Vector3f(-0.5f,  0.5f,  0.5f),
            new Vector3f(-0.5f, -0.5f,  0.5f),
            new Vector3f(0.5f, -0.5f,  0.5f),
            new Vector3f(0.5f,  0.5f,  0.5f),
            new Vector3f(-0.5f,  0.5f, -0.5f),
            new Vector3f(0.5f,  0.5f, -0.5f),
            new Vector3f(-0.5f, -0.5f, -0.5f),
            new Vector3f(0.5f, -0.5f, -0.5f)
        };
        Vector3f[] colors = new Vector3f[]{
            new Vector3f(0.5f, 0.0f, 0.0f),
            new Vector3f(0.0f, 0.5f, 0.0f),
            new Vector3f(0.0f, 0.0f, 0.5f),
            new Vector3f(0.0f, 0.5f, 0.5f),
            new Vector3f(0.5f, 0.0f, 0.0f),
            new Vector3f(0.0f, 0.5f, 0.0f),
            new Vector3f(0.0f, 0.0f, 0.5f),
            new Vector3f(0.0f, 0.5f, 0.5f)
        };
        int[] indices = new int[]{
            // Front face
            0, 1, 3, 3, 1, 2,
            // Top Face
            4, 0, 3, 5, 4, 3,
            // Right face
            3, 2, 7, 5, 3, 7,
            // Left face
            6, 1, 0, 6, 0, 4,
            // Bottom face
            2, 1, 6, 2, 6, 7,
            // Back face
            7, 6, 4, 7, 4, 5,
        };

        mesh = new Mesh(vertices, colors, indices);
        GameItem gameItem = new GameItem(mesh);
        gameItem.setPosition(0, 0, -2);
        gameItems = new GameItem[] { gameItem };
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
            float posX = itemPos.x + displxInc * 0.01f;
            float posY = itemPos.y + displyInc * 0.01f;
            float posZ = itemPos.z + displzInc * 0.01f;
            gameItem.setPosition(posX, posY, posZ);

            // Update scale
            float scale = gameItem.getScale();
            scale += scaleInc * 0.05f;
            if ( scale < 0 ) {
                scale = 0;
            }
            gameItem.setScale(scale);

            // Update rotation angle
            float rotation = gameItem.getRotation().z + 1.5f;
            if ( rotation > 360 ) {
                rotation = 0;
            }
            gameItem.setRotation(rotation, rotation, rotation);
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, gameItems);
    }

    @Override
    public void destroy() {
        renderer.destroy();
        for (GameItem gameItem : gameItems){
            gameItem.getMesh().destroy();
        }
    }
}
