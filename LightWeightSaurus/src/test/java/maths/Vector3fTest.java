package maths;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Vector3fTest {

    Vector3f vector3f;
    private static final double DELTA = 1e-15;
    @Before
    public void setUp(){
        vector3f = new Vector3f(0, 0, 0);
    }
    @Test
    public void set() {
        vector3f.set(1,1,1);
        assertEquals("Vector3f{x=1.0, y=1.0, z=1.0}", vector3f.toString());
    }

    @Test
    public void getX() {
        vector3f.setX(1);
        assertEquals(1.0f, vector3f.getX(), DELTA);
    }

    @Test
    public void getY() {
        vector3f.setY(1);
        assertEquals(1.0f, vector3f.getY(), DELTA);
    }

    @Test
    public void getZ() {
        vector3f.setZ(1);
        assertEquals(1.0f, vector3f.getZ(), DELTA);
    }

}
