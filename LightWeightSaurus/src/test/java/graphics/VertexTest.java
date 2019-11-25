package graphics;

import maths.Vector3f;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VertexTest {

    Vertex vertex;

    @Before
    public void setUp(){
        vertex = new Vertex(new Vector3f(1,1,1), new Vector3f(1,1,1));

    }
    @Test
    public void getPosition() {
        assertEquals(new Vector3f(1,1,1), vertex.getPosition());
    }

    @Test
    public void getColor() {
        assertEquals(new Vector3f(1,1,1), vertex.getColor());
    }
}
