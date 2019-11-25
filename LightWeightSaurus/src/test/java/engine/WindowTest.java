package engine;

import org.junit.Test;

import static org.junit.Assert.*;

public class WindowTest {


    @Test
    public void getTitle() {
        Window window = new Window(300, 200, "GAME");
        assertEquals(window.getTitle(), "GAME");
    }


    @Test
    public void create() {
        Window window = new Window(300, 200, "GAME");
        window.create();
        assertFalse(window.getWindow() == 0);
    }
}
