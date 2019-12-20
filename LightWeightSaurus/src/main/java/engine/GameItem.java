package engine;

import graphics.Mesh;
import org.joml.Vector3f;

public class GameItem {

    private final Mesh mesh;

    private final Vector3f position;

    private float scale;

    private final Vector3f rotation;


    /**
     * Konstruktori joka luo pelin sisäisen objektin.
     *
     * @param mesh sisältää tarvittavan tiedon grafiikasta.
     */
    public GameItem(Mesh mesh) {
        this.mesh = mesh;
        position = new Vector3f(0, 0, 0);
        scale = 1;
        rotation = new Vector3f(0, 0, 0);
    }


    /**
     * Asettaa GameItemin lokaation 3D tilassa.
     *
     * @param x x-akseli
     * @param y y-akseli
     * @param z z-akseli
     */
    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    /**
     * Asettaa GameItemin rotaation 3D tilassa.
     *
     * @param x x-akseli
     * @param y y-akseli
     * @param z z-akseli
     */
    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public Mesh getMesh() {
        return mesh;
    }
}
