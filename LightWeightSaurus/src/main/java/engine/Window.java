package engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {
    private float backgroundX, backgroundY, backgroundZ;
    private int width, height;
    private String title;
    private long windowHandle;
    private GLFWWindowSizeCallback sizeCallback;
    private boolean isResized;
    private boolean isFullscreen;

    private boolean vSync;

    /**
     * Luo Window olion.
     * @param title Titteli joka tulee näkyviin ikkunan ylälaitaan
     * @param width Ikkunan leveys.
     * @param height Ikkunan korkeus.
     * @param vSync Vsync päällä vai ei.
     */
    public Window(String title, int width, int height, boolean vSync) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.isResized = false;
        this.vSync = vSync;
    }

    /**
     * Alustaa ikkunan.
     * @param showWindow Näyttääkö ikkunan vai ei. Testejä varten voidaan haluta että ei.
     *                   Mutta suorituksen aikana tämä pitää olla true.
     */
    public void init(boolean showWindow) {
        //error callback.
        GLFWErrorCallback.createPrint(System.err).set();

        //init glfw
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to init GLFW");
        }


        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

        windowHandle = GLFW.glfwCreateWindow(width, height, title, isFullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);

        if (windowHandle == 0) {
            throw new RuntimeException("Failed to create GLFW window");
        }


        createCallbacks();

        GLFWVidMode videoMode = glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

        glfwSetWindowPos(windowHandle,
            (videoMode.width() - width) / 2,
            (videoMode.height() - height) / 2
        );

        glfwMakeContextCurrent(windowHandle);

        if (isvSync()) {
            glfwSwapInterval(1);
        }

        if (showWindow) {
            GLFW.glfwShowWindow(windowHandle);
        }

        GL.createCapabilities();
        glClearColor(backgroundX, backgroundY, backgroundZ, 1.0f);
        glEnable(GL_DEPTH_TEST);
    }

    /**
     *  Luo ikkunan koon muokkaamisen mahdollistavan komennon.
     */
    private void createCallbacks() {
        sizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                Window.this.width = width;
                Window.this.height = height;
                isResized = true;
            }
        };
        GLFW.glfwSetWindowSizeCallback(windowHandle, sizeCallback);
    }

    /**
     * Päivittää ikkunan.
     */
    public void update() {
        if (isResized) {
            GL11.glViewport(0, 0, width, height);
            isResized = false;
        }

        glfwSwapBuffers(windowHandle);
        GLFW.glfwPollEvents();
    }

    /**
     * Vapauttaa ikkunan resurssit.
     */
    public void destroy() {
        sizeCallback.free();
        GLFW.glfwWindowShouldClose(windowHandle);
        GLFW.glfwDestroyWindow(windowHandle);
    }

    /**
     * Asettaa taustan värin.
     * @param r Puna.
     * @param g Vihreä.
     * @param b Sininen.
     */
    public void setClearColor(float r, float g, float b) {
        backgroundX = r;
        backgroundY = g;
        backgroundZ = b;
        glClearColor(r, g, b, 1.0f);
    }

    public boolean isFullScreen() {
        return isFullscreen;
    }

    /**
     * Kertoo pitäiskö ikkuna sulkea.
     * @return boolean joka ilmoittaa suljetaanko ikkuna.
     */
    public boolean close() {
        return GLFW.glfwWindowShouldClose(windowHandle);
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

    public long getWindowHandle() {
        return windowHandle;
    }

    /**
     * Onko Vsync päällä.
     * @return Onko päällä vai ei.
     */
    public boolean isvSync() {
        return vSync;
    }
}
