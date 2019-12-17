package engine;

import graphics.Mesh;
import graphics.Texture;
import org.joml.Vector3f;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.OBJLoader;

import static org.junit.Assert.*;

public class GameItemTest {

    GameItem gameItem;

    Mesh mesh;

    @Before
    public void setUp() throws Exception {
        Window window = new Window("TEST", 100, 100, false);
        window.init(false);

        Texture texture = new Texture("/textures/grassblock.png");
        mesh = OBJLoader.loadMesh("/models/cube.obj");
        gameItem = new GameItem(mesh);
    }

    @Test
    public void setPosition() {
        gameItem.setPosition(1, 2, 3);
        assertEquals(new Vector3f(1, 2, 3), gameItem.getPosition());
    }

    @Test
    public void getPosition() {
        assertEquals(new Vector3f(0, 0, 0), gameItem.getPosition());
    }

    @Test
    public void setScale() {
        gameItem.setScale(2);
        assertEquals(2, gameItem.getScale(), 0.01);
    }

    @Test
    public void getScale() {
        assertEquals(1, gameItem.getScale(), 0.01);
    }

    @Test
    public void getRotation() {
        assertEquals(new Vector3f(0, 0, 0), gameItem.getRotation());
    }

    @Test
    public void setRotation() {
        gameItem.setRotation(1, 5, 10);
        assertEquals(new Vector3f(1, 5, 10), gameItem.getRotation());
    }

    @Test
    public void getMesh() {
        assertEquals(mesh, gameItem.getMesh());
    }
}
