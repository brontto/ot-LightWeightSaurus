package engine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WindowTest {

    Window window;

    @Before
    public void setUp(){
        window = new Window("TEST", 100, 100, true);
    }

    @Test
    public void getTitle() {
        assertEquals(window.getTitle(), "TEST");
    }

    @Test
    public void getHeight(){
        assertEquals(100, window.getHeight());
    }

    @Test
    public void getWidth(){
        assertEquals(100, window.getWidth());
    }

    @Test
    public void init() {
        window.init();
        assertFalse(window.getWindowHandle() == 0);
    }
}
