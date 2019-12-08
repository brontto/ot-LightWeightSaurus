package engine;

import graphics.Mesh;
import graphics.Texture;
import org.joml.Vector3f;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class GameItemTest {

    GameItem gameItem;

    Mesh mesh;

    @Before
    public void setUp() throws Exception {
        Window window = new Window("TEST", 100, 100, false);
        window.init(false);
        float[] vertices = new float[]{
            // V0
            -0.5f, 0.5f, 0.5f,
            // V1
            -0.5f, -0.5f, 0.5f,
            // V2
            0.5f, -0.5f, 0.5f,
            // V3
            0.5f, 0.5f, 0.5f,
            // V4
            -0.5f, 0.5f, -0.5f,
            // V5
            0.5f, 0.5f, -0.5f,
            // V6
            -0.5f, -0.5f, -0.5f,
            // V7
            0.5f, -0.5f, -0.5f,

            // For text coords in top face
            // V8: V4 repeated
            -0.5f, 0.5f, -0.5f,
            // V9: V5 repeated
            0.5f, 0.5f, -0.5f,
            // V10: V0 repeated
            -0.5f, 0.5f, 0.5f,
            // V11: V3 repeated
            0.5f, 0.5f, 0.5f,

            // For text coords in right face
            // V12: V3 repeated
            0.5f, 0.5f, 0.5f,
            // V13: V2 repeated
            0.5f, -0.5f, 0.5f,

            // For text coords in left face
            // V14: V0 repeated
            -0.5f, 0.5f, 0.5f,
            // V15: V1 repeated
            -0.5f, -0.5f, 0.5f,

            // For text coords in bottom face
            // V16: V6 repeated
            -0.5f, -0.5f, -0.5f,
            // V17: V7 repeated
            0.5f, -0.5f, -0.5f,
            // V18: V1 repeated
            -0.5f, -0.5f, 0.5f,
            // V19: V2 repeated
            0.5f, -0.5f, 0.5f,
        };
        float[] textCoords = new float[]{
            0.0f, 0.0f,
            0.0f, 0.5f,
            0.5f, 0.5f,
            0.5f, 0.0f,

            0.0f, 0.0f,
            0.5f, 0.0f,
            0.0f, 0.5f,
            0.5f, 0.5f,

            // For text coords in top face
            0.0f, 0.5f,
            0.5f, 0.5f,
            0.0f, 1.0f,
            0.5f, 1.0f,

            // For text coords in right face
            0.0f, 0.0f,
            0.0f, 0.5f,

            // For text coords in left face
            0.5f, 0.0f,
            0.5f, 0.5f,

            // For text coords in bottom face
            0.5f, 0.0f,
            1.0f, 0.0f,
            0.5f, 0.5f,
            1.0f, 0.5f,
        };
        int[] indices = new int[]{
            // Front face
            0, 1, 3, 3, 1, 2,
            // Top Face
            4, 0, 3, 5, 4, 3,
            // Right face
            3, 2, 7, 5, 3, 7,
            // Left face
            6, 1, 0, 6, 0, 4,
            // Bottom face
            2, 1, 6, 2, 6, 7,
            // Back face
            7, 6, 4, 7, 4, 5,
        };
        Texture texture = new Texture("/textures/grassblock.png");
        mesh = new Mesh(vertices, textCoords, indices, texture);
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
