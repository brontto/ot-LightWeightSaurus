package graphics;

import engine.Window;
import maths.Vector3f;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MeshTest {

    Mesh mesh;

//    @Before
//    public void setUp(){
//        mesh = new Mesh(new Vertex[]{
//                new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f)),
//                new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f)),
//                new Vertex(new Vector3f(0.5f, -0.5f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f)),
//                new Vertex(new Vector3f(0.5f, 0.5f, 0.0f), new Vector3f(1.0f, 1.0f, 0.0f))
//        }, new int[]{
//                0, 1, 2,
//                0, 3, 2
//        });
//    }
    /*@Test
    public void create() {
        Window window = new Window(100, 100, "GAME");
        window.init();
        mesh.create();
        assertNotEquals(0, mesh.getIBO());
        assertNotEquals(0, mesh.getVAO());
        assertNotEquals(0, mesh.getCBO());
        assertNotEquals(0, mesh.getPBO());
    }*/

    @Test
    public void destroy() {
    }

    @Test
    public void getVertices() {
    }

    @Test
    public void getIndices() {
    }

    @Test
    public void getVAO() {
    }

    @Test
    public void getPBO() {
    }

    @Test
    public void getCBO() {
    }

    @Test
    public void getIBO() {
    }
}
