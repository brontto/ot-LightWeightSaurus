package graphics;

import org.joml.Vector3f;

public class Camera {

    private final Vector3f position;

    private final Vector3f rotation;


    /**
     * Luo kameran positioon 0,0,0, rotaatiolla 0,0,0.
     */
    public Camera() {
        position = new Vector3f(0, 0, 0);
        rotation = new Vector3f(0, 0, 0);
    }

    /**
     * Luo kameran haluttuun kohtaan, halutulla rotaatiolla.
     *
     * @param position Haluttu kohta 3d tilassa.
     * @param rotation Haluttu rotaatio 3d tilassa.
     */
    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }


    /**
     * Siirtää kameran haluttuun positioon.
     *
     * @param x Paikka X-akselilla.
     * @param y Paikka Y-akselilla.
     * @param z Paikka Z-akselilla.
     */
    public void setPosition(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
    }

    public Vector3f getPosition() {
        return position;
    }


    /**
     * Siirtää kameraa annettuihin suuntiin.
     *
     * @param x X-akselin siirros.
     * @param y Y-akselin siirros.
     * @param z Z-akselin siirros.
     */
    public void movePosition(float x, float y, float z) {
        if (z != 0) {
            position.x += (float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * z;
            position.z += (float) Math.cos(Math.toRadians(rotation.y)) * z;
        }

        if (x != 0) {
            position.x += (float) Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * x;
            position.z += (float) Math.cos(Math.toRadians(rotation.y - 90)) * x;
        }
        position.y += y;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    /**
     * Kääntää kameran haluttuun suuntaan.
     *
     * @param x Rotaatio X-askelilla.
     * @param y Rotaatio Y-askelilla.
     * @param z Rotaatio X-askelilla.
     */
    public void setRotation(float x, float y, float z) {
        rotation.x = x;
        rotation.y = y;
        rotation.z = z;
    }

    /**
     * Muokkaa kameran rotaatiota annettuihin suuntiin halutun määrän.
     *
     * @param offsetX X-akselin siirros.
     * @param offsetY Y-akselin siirros.
     * @param offsetZ Z-akselin siirros.
     */
    public void moveRotation(float offsetX, float offsetY, float offsetZ) {
        rotation.x += offsetX;
        rotation.y += offsetY;
        rotation.z += offsetZ;
    }

}
