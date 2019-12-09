package engine;

import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

    private static boolean[] keys = new boolean[GLFW_KEY_LAST];
    private static boolean[] buttons = new boolean[GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX, mouseY;
    private static double scrollX, scrollY;


    private static GLFWKeyCallback keyboard;
    private static GLFWCursorPosCallback mouseMove;
    private static GLFWMouseButtonCallback mouseButtons;
    private static GLFWScrollCallback mouseScroll;

    private static boolean inited;

    /**
     * Alustaa inputit oikeaan ikkunaan.
     * @param windowsHandle ikkunan tunniste.
     */
    public static void init(Long windowsHandle) {

        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW_RELEASE);
            }
        };

        mouseMove = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };

        mouseButtons = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW_RELEASE);
            }
        };

        mouseScroll = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xoffset, double yoffset) {
                scrollX += xoffset;
                scrollY += yoffset;
            }
        };

        org.lwjgl.glfw.GLFW.glfwSetKeyCallback(windowsHandle, keyboard);
        GLFW.glfwSetCursorPosCallback(windowsHandle, mouseMove);
        GLFW.glfwSetMouseButtonCallback(windowsHandle, mouseButtons);
        GLFW.glfwSetScrollCallback(windowsHandle, mouseScroll);

        inited = true;
    }

    /**
     * Vapauttaa resurssit moottirn lopetettua.
     */
    public static void destroy() {
        keyboard.free();
        mouseMove.free();
        mouseButtons.free();
        mouseScroll.free();

    }

    public static double getMouseY() {
        return mouseY;
    }

    public static double getMouseX() {
        return mouseX;
    }

    public static double getScrollY() {
        return scrollY;
    }

    public static double getScrollX() {
        return scrollX;
    }

    public static GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }

    public static GLFWCursorPosCallback getMouseMoveCallback() {
        return mouseMove;
    }

    public static GLFWMouseButtonCallback getMouseButtonsCallback() {
        return mouseButtons;
    }

    public static GLFWScrollCallback getMouseScrollCallback() {
        return mouseScroll;
    }

    /**
     * Palauttaa halutun näppäin inputin tilan.
     * @param key Haluttu näppäin.
     * @return Tila. Onko painettu vai ei.
     */
    public static boolean isKeyDown(int key) {
        return keys[key];
    }

    /**
     * Palauttaa halutun hiiren painikkeen inputin tilan.
     * @param key Haluttu painike.
     * @return Tila. Onko painettu vai ei.
     */
    public static boolean isButtonDown(int key) {
        return buttons[key];
    }

    public static boolean isInitialized() {
        return inited;
    }
}