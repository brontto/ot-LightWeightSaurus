package game;

import engine.IGameLogic;
import engine.Input;
import engine.Window;
import graphics.Mesh;
import graphics.Renderer;
import maths.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class DummyGame implements IGameLogic {

    private float color = 0.0f;

    private final Renderer renderer;

    private Mesh mesh;

    public DummyGame() {
        this.renderer = new Renderer();
    }

    @Override
    public void init() throws Exception {
        renderer.init();
        Vector3f[] vertices = new  Vector3f[]{
                new Vector3f(-0.5f,  0.5f, 0.0f),
                new Vector3f(-0.5f, -0.5f, 0.0f),
                new Vector3f(0.5f, -0.5f, 0.0f),
                new Vector3f(0.5f,  0.5f, 0.0f)
        };
        Vector3f[] colors = new Vector3f[]{
                new Vector3f(0.5f, 0.0f, 0.0f),
                new Vector3f(0.0f, 0.5f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.5f),
                new Vector3f(0.0f, 0.5f, 0.5f)
        };
        int[] indices = new int[]{
                0, 1, 3, 3, 1, 2,
        };

        mesh = new Mesh(vertices, colors, indices);

    }

    @Override
    public void update(float interval) {
        if(Input.isKeyDown(GLFW_KEY_UP)){
            color += 0.01;
        }else if(Input.isKeyDown(GLFW_KEY_DOWN)){
            color -= 0.01;
        }
        if (color > 1) {
            color = 1.0f;
        } else if (color < 0) {
            color = 0.0f;
        }
    }

    @Override
    public void render(Window window) {
        window.setBackgroundColor(color, color, color);
        renderer.render(mesh);
    }

    @Override
    public void destroy() {
        renderer.destroy();
        mesh.destroy();
    }
}
