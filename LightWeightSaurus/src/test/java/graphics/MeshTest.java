package graphics;

import engine.Window;
import org.joml.Vector3f;
import org.junit.Before;
import org.junit.Test;
import utils.OBJLoader;

import static org.junit.Assert.*;

public class MeshTest {

    Mesh mesh;
    Window window;

    @Before
    public void setUp() throws Exception {
        window = new Window("TEST", 100, 100, false);
        window.init(false);

        Texture texture = new Texture("/textures/grassblock.png");
        mesh = OBJLoader.loadMesh("/models/cube.obj");
    }


    @Test
    public void constructor() {
        assertNotEquals(0, mesh.getTcVboId());
        assertNotEquals(0, mesh.getIdxVboId());
        assertNotEquals(0, mesh.getPosVboId());
        assertNotEquals(0, mesh.getVaoId());
        assertNotEquals(0, mesh.getVertexCount());
    }

    @Test
    public void destroy() {
    }
}
