package engine;

import game.RollingCubeDemo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EngineTest {

    Engine engine;
    IGameLogic gameLogic;
    @Before
    public void setUp() throws Exception {
        gameLogic = new RollingCubeDemo();
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
        assertTrue(Input.isInitialized());

    }

    @Test
    public void run() throws InterruptedException {
        engine.run(1);
        assertTrue(engine.isInited());
    }

}
