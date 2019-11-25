package Engine;

import Graphics.Mesh;
import Graphics.Renderer;
import Graphics.Shader;
import Graphics.Vertex;
import Maths.Vector3f;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class Engine implements Runnable {

    public Thread thread;
    public Window window;

    public final int WIDTH = 1280, HEIGHT = 720;

    public Shader shader;
    public Renderer renderer;
    public Mesh mesh = new Mesh(new Vertex[] {
            new Vertex(new Vector3f(-0.5f,  0.5f, 0.0f), new Vector3f(1.0f, 0.0f,0.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 1.0f,0.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 0.0f,1.0f)),
            new Vertex(new Vector3f( 0.5f,  0.5f, 0.0f), new Vector3f(1.0f, 1.0f,0.0f))
    }, new int[] {
            0, 1, 2,
            0, 3, 2
    });



    private boolean keepRunning;

    public void start() {
        thread = new Thread(this);
        thread.start(); //tää kutsuu run metodia
    }

    public void init() throws Exception {

        window = new Window(WIDTH, HEIGHT, "GAME");
        shader = new Shader("/shaders/mainVertex.glsl", "/shaders/mainFragment.glsl");
        renderer = new Renderer(shader);

        window.setBackgroundColor(1.0f, 0.5f, 0.5f);

        window.create();
        mesh.create();
        shader.create();
        //window.setFullScreen(true);
        Input.Init(window.getWindow());
        keepRunning = true;
    }

    public void run() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (keepRunning) {
            update();
            render();

        }
        destroy();
    }


    private void update() {
        //System.out.println("Updating Game");
        window.update();

        if (Input.isKeyDown(GLFW_KEY_ESCAPE) || window.close()) {
            keepRunning = false;
        }

        if (Input.isKeyDown(GLFW_KEY_F11)) {
            window.setFullScreen(!window.isFullScreen());
        }

        if (Input.isButtonDown(GLFW_MOUSE_BUTTON_LEFT)) {
            System.out.println("X: " + Input.getMouseX() + ", Y: " + Input.getMouseY());
        }
    }


    private void render() {
        //System.out.println("Rendering Game");
        renderer.renderMesh(mesh);
        window.swapBuffers();
    }

    private void destroy() {
        Input.destroy();
        window.destroy();
        mesh.destroy();
        shader.destroy();
        GLFW.glfwTerminate();
    }
}
