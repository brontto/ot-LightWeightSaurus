package Engine;

import Maths.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;


public class Window {


    private int width, height;
    private String title;

    private long window;

    public int frames;
    private long time;

    private Vector3f background = new Vector3f(0, 0, 0);

    private GLFWWindowSizeCallback sizeCallback;

    private boolean isResized;
    private boolean fullScreen;

    private int[] windowPosX = new int[1], windowPosY = new int[1];

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.time = System.currentTimeMillis();
    }

    public void create() {
        if (!GLFW.glfwInit()) {
            System.err.println("ERROR: GLFW was not initialized.");
            return;
        }

        window = GLFW.glfwCreateWindow(width, height, title, fullScreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);

        if (window == 0) {
            System.err.println("ERROR: Window was not created.");
        }

        GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        windowPosX[0] = (vidMode.width() - width) / 2;
        windowPosY[0] = (vidMode.height() - height) / 2;
        GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        createCallbacks();

        GLFW.glfwShowWindow(window);

        //määrää kuinka monta framea bufferi odottaa ennkuin vaihtaa
        //GLFW.glfwSwapInterval(1);

    }

    private void createCallbacks() {
        sizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                Window.this.width = width;
                Window.this.height = height;
                isResized = true;
            }
        };
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
    }

    public void update() {
        if (isResized) {
            GL11.glViewport(0, 0, width, height);
            isResized = false;
        }

        GL11.glClearColor(background.getX(), background.getY(), background.getZ(), 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);
        GLFW.glfwPollEvents();//tätä on pakko kutsuu main threadistaaaa
        frames++;
        if (System.currentTimeMillis() > time + 1000) {
            GLFW.glfwSetWindowTitle(window, title + " | FPS: " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }


    public void swapBuffers() {
        //tää voitas kutsuu secondary thread
        GLFW.glfwSwapBuffers(window);

    }

    public void setBackgroundColor(float r, float g, float b) {
        background.set(r, g, b);
    }

    public void destroy() {
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        sizeCallback.free();
    }

    public boolean close() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public long getWindow() {
        return window;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
        isResized = true;
        if (fullScreen) {
            GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, width, height, 0);
        } else {
            GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, 0);
        }
    }
}
