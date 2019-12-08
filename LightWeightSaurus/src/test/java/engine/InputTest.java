package engine;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lwjgl.system.CallbackI;

import static org.junit.Assert.*;

public class InputTest {


    @Before
    public void setUp() {
        Window window = new Window("Test", 100, 100, true);
        window.init(false);
        Input.init(window.getWindowHandle());
    }

    @Test
    public void mouseCallBacksInited() {
        assertNotNull(Input.getKeyboardCallback());
        assertNotNull(Input.getMouseButtonsCallback());
        assertNotNull(Input.getMouseMoveCallback());
        assertNotNull(Input.getMouseScrollCallback());
    }

    @Test
    public void keyCallBacksInited() {
        assertNotNull(Input.isKeyDown(3));
        assertNotNull(Input.isButtonDown(1));
    }

    @Test
    public void mouseInited() {
        assertNotNull(Input.getMouseX());
        assertNotNull(Input.getMouseY());
        assertNotNull(Input.getScrollX());
        assertNotNull(Input.getScrollY());
    }


}
