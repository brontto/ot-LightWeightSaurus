package graphics;

import engine.GameItem;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformation {

    private final Matrix4f projectionMatrix;

    private final Matrix4f modelViewMatrix;

    private final Matrix4f viewMatrix;

    /**
     * Luo ja lustaa objektin.
     */
    public Transformation() {
        modelViewMatrix = new Matrix4f();
        projectionMatrix = new Matrix4f();
        viewMatrix = new Matrix4f();
    }

    /**
     * Luo projektiomatriisin jonka avulla voidaan muuttaa 3d koordinaatit 2d ruudulle.
     *
     * @param fov    Kuinka pitkälle kuvaa piirretään.
     * @param width  Kuinka leveä kuva on.
     * @param height Kuinka korkea kuva on.
     * @param zNear  Mikä on lähin piirrettävä etäisyys.
     * @param zFar   Mikä on pisin piirrettävä etäisyys.
     * @return Projektiomatriisi.
     */
    public final Matrix4f getProjectionMatrix(float fov, float width, float height, float zNear, float zFar) {
        float aspectRatio = width / height;
        projectionMatrix.identity();
        projectionMatrix.perspective(fov, aspectRatio, zNear, zFar);
        return projectionMatrix;
    }

    /**
     * Laskee uuden matriisin gameItemin nykyisen rotaation mukaan.
     * Ja kertoo sen kameran tiedot sisältävällä matriisiilla.
     *
     * @param gameItem   GameItem joka sisältää tiedon sen rotaatiosta.
     * @param viewMatrix Matriisi joka sisältää tiedot kamerasta.
     * @return Matriisin joka kertoo miten gameItem pitää pirtää.
     */
    public Matrix4f getModelViewMatrix(GameItem gameItem, Matrix4f viewMatrix) {
        Vector3f rotation = gameItem.getRotation();
        modelViewMatrix.identity().translate(gameItem.getPosition())
                .rotateX((float) Math.toRadians(-rotation.x))
                .rotateY((float) Math.toRadians(-rotation.y))
                .rotateZ((float) Math.toRadians(-rotation.z))
                .scale(gameItem.getScale());
        Matrix4f viewCurr = new Matrix4f(viewMatrix);
        return viewCurr.mul(modelViewMatrix);
    }

    /**
     * Voidaan päivittää viewMatriksin tilaa.
     *
     * @param camera Camera objekti josta heataan rotaatio ja positio.
     * @return Matrixin jolla lasketaan objektien sijainteja suhteessa kameraan.
     */
    public Matrix4f getViewMatrix(Camera camera) {
        Vector3f position = camera.getPosition();
        Vector3f rotation = camera.getRotation();

        viewMatrix.identity();

        viewMatrix.rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0))
                .rotate((float) Math.toRadians(rotation.y), new Vector3f(0, 1, 0));

        viewMatrix.translate(-position.x, -position.y, position.z);
        return viewMatrix;
    }
}
