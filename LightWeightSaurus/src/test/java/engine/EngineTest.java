package engine;

import game.DummyGame;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class EngineTest {

    Engine engine;
    IGameLogic gameLogic;
    @Before
    public void setUp() throws Exception {
        gameLogic = new DummyGame();
        engine = new Engine("TEST", true, gameLogic);
    }

    @Test
    public void constructor(){
        assertNotNull(engine.window);
        assertNotNull(engine.getGameLogic());
    }
    @Test
    public void init() throws Exception {
        engine.init();
        assertNotEquals(0, engine.window.getWindowHandle());
        assertNotEquals(0, Time.getLastLoopTime());
        assertTrue(Input.isInited());
    }

    @Test
    public void run() {
    }
}
