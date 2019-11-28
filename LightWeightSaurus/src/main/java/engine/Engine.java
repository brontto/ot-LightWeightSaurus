package engine;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;

public class Engine implements Runnable {

    public static final int TARGET_FPS = 75;

    public static final int TARGET_UPS = 30;

    public static final int WIDTH = 1280, HEIGHT = 720;

    public Window window;

    private IGameLogic gameLogic;



    private boolean keepRunning;

    public Engine(String windowTitle, boolean vSync, IGameLogic gameLogic) throws Exception {
        window = new Window(windowTitle, WIDTH, HEIGHT, vSync);
        this.gameLogic = gameLogic;
    }





    public void init() throws Exception {
        window.init();
        Input.init(window.getWindowHandle());
        Time.init();
        gameLogic.init();
    }

    public void run() {
        try {
            init();
            loop();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            destroy();
        }


    }

    private void loop() {

        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f/ TARGET_UPS;

        keepRunning = true;
        while (keepRunning) {
            elapsedTime = Time.getElapsedTime();
            accumulator += elapsedTime;

            while(accumulator >= interval){
                update(interval);
                accumulator -= interval;
            }

            render();

            if(!window.isvSync()){
                sync();
            }
        }
    }



    private void update(float interval) {

        gameLogic.update(interval);

        if (Input.isKeyDown(GLFW_KEY_ESCAPE) || window.close()) {
            keepRunning = false;
        }

        if (Input.isButtonDown(GLFW_MOUSE_BUTTON_LEFT)) {
            System.out.println("X: " + Input.getMouseX() + ", Y: " + Input.getMouseY());
        }
    }

    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = Time.getLastLoopTime() + loopSlot;
        while (Time.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
            }
        }
    }


    private void render() {
        gameLogic.render(window);
        window.update();
    }

    private void destroy() {
        Input.destroy();
        window.destroy();
        gameLogic.destroy();
        GLFW.glfwTerminate();
    }
}
