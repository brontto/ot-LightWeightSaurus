package Engine;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;

public class CoreLoop implements Runnable {

    public Thread thread;
    public Window window;

    public final int WIDTH = 1280, HEIGHT = 720;

    public Input input;

    private boolean keepRunning;

    public void start() {
        thread = new Thread(this);
        thread.run();
    }

    public void init() {
        System.out.println("Initialized Game");
        window = new Window(WIDTH, HEIGHT, "GAME");
        window.setBackgroundColor(0.5f, 0.5f, 0.5f);
        //window.setFullScreen(true);
        window.create();

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
        window.swapBuffers();
    }

    private void destroy() {
        Input.destroy();
        window.destroy();

        GLFW.glfwTerminate();
    }
}
