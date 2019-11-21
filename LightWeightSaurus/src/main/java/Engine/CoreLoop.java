package Engine;

import Graphics.Mesh;
import Graphics.Renderer;
import Graphics.Vertex;
import Maths.Vector3f;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;

public class CoreLoop implements Runnable {

    public Thread thread;
    public Window window;

    public final int WIDTH = 1280, HEIGHT = 720;

    public Renderer renderer;
    public Mesh mesh = new Mesh(new Vertex[]{
            new Vertex(new Vector3f(-0.5F,0.5F,0.0f)),
            new Vertex(new Vector3f(-0.5F,-0.5F,0.0f)),
            new Vertex(new Vector3f(0.5F,-0.5F,0.0f)),
            new Vertex(new Vector3f(0.5F,0.5F,0.0f))
    }, new int[]{
            0,1,2,
            0,3,2
    });

    public Input input;

    private boolean keepRunning;

    public void start() {
        thread = new Thread(this);
        thread.run();
    }

    public void init() {
        System.out.println("Initialized Game");
        window = new Window(WIDTH, HEIGHT, "GAME");
        renderer = new Renderer();
        window.setBackgroundColor(1.0f, 0.5f, 0.5f);
        //window.setFullScreen(true);
        window.create();

        mesh.create();
        Input.Init(window.getWindow());
        keepRunning = true;
    }

    public void run() {
        init();
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

        GLFW.glfwTerminate();
    }
}
