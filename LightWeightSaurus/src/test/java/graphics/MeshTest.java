package graphics;

import engine.Window;
import org.joml.Vector3f;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MeshTest {

    Mesh mesh;
    Window window;

    @Before
    public void setUp() throws Exception {
        window = new Window("TEST", 100, 100, false);
        window.init();
        Vector3f[] vertices = new Vector3f[]{
                new Vector3f(-0.5f, 0.5f, 0.0f),
                new Vector3f(-0.5f, -0.5f, 0.0f),
                new Vector3f(0.5f, -0.5f, 0.0f),
                new Vector3f(0.5f, 0.5f, 0.0f)
        };
        Vector3f[] colors = new Vector3f[]{
                new Vector3f(0.5f, 0.0f, 0.0f),
                new Vector3f(0.0f, 0.5f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.5f),
                new Vector3f(0.0f, 0.5f, 0.5f)
        };
        int[] indices = new int[]{
                0, 1, 3, 3, 1, 2,
        };
        mesh = new Mesh(vertices, colors, indices);
    }
    @Test
    public void constructor() {
        assertNotEquals(0, mesh.getColorVboId());
        assertNotEquals(0, mesh.getIdxVboId());
        assertNotEquals(0, mesh.getPosVboId());
        assertNotEquals(0, mesh.getVaoId());
        assertNotEquals(0, mesh.getVertexCount());
    }

    @Test
    public void destroy() {
    }
}
