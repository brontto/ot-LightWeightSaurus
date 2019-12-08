package graphics;

import org.joml.Vector3f;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class CameraTest {

    Camera camera;

    @Before
    public void setUp(){
        camera = new Camera();
    }

    @Test
    public void setPosition() {
        camera.setPosition(1,5,10);
        assertEquals(new Vector3f(1,5,10), camera.getPosition());
    }

    @Test
    public void getPosition() {
        assertEquals(new Vector3f(0, 0, 0), camera.getPosition());
    }

    @Test
    public void movePosition() {
        Vector3f v3 = camera.getPosition();
        camera.movePosition(1, 1, 1);
        v3.x += 1;
        v3.y += 1;
        v3.z += 1;
        assertEquals(v3, camera.getPosition());
    }

    @Test
    public void getRotation() {
        assertEquals(new Vector3f(0, 0, 0), camera.getRotation());
    }

    @Test
    public void setRotation() {
        camera.setRotation(1,5,10);
        assertEquals(new Vector3f(1,5,10), camera.getRotation());
    }

    @Test
    public void moveRotation() {
        Vector3f v3 = camera.getRotation();
        camera.moveRotation(1, 1, 1);
        v3.x += 1;
        v3.y += 1;
        v3.z += 1;
        assertEquals(v3, camera.getRotation());
    }
}
