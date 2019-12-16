package graphics;

import engine.Window;
import engine.GameItem;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    private static final float FOV = (float) Math.toRadians(60.0f);

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 1000.0f;

    private Matrix4f projectionMatrix;

    private ShaderProgram shaderProgram;

    private final Transformation transformation;

    /**
     * Luo Renderer objektin ja alustaa sille Tranformation objektin.
     */
    public Renderer() {
        transformation = new Transformation();
    }

    /**
     * Alustaa Rendererin toimintaan.
     * @param window Ikkuna kuvasuhteen laskemista varten
     */
    public void init(Window window) throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader("/shaders/mainVertex.glsl");
        shaderProgram.createFragmentShader("/shaders/mainFragment.glsl");
        shaderProgram.link();

        float aspectRatio = (float) window.getWidth() / window.getHeight();
        projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("modelViewMatrix");
        shaderProgram.createUniform("texture_sampler");

        window.setClearColor(0, 0, 0);
    }

    /**
     * Huolehtii gameItemien piirtämisestä oikeaan paikkaan.
     * @param window Ikkuna projektiomatriisin laskuja varten.
     * @param camera Kamera objecti etäisyyksien laskemiseen.
     * @param gameItems Kaikki gameItemit joitka scenestä löytyy.
     */
    public void render(Window window, Camera camera, GameItem[] gameItems) {
        clear();

        shaderProgram.bind();

        Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV,
                window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        Matrix4f viewMatrix = transformation.getViewMatrix(camera);

        shaderProgram.setUniform("texture_sampler", 0);

        for (GameItem gameItem : gameItems) {
            Matrix4f modelViewMatrix = transformation.getModelViewMatrix(gameItem, viewMatrix);
            shaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
            gameItem.getMesh().render();
        }

        shaderProgram.unbind();
    }

    private void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    /**
     * Vapauttaa resurssit.
     */
    public void destroy() {
        if (shaderProgram != null) {
            shaderProgram.destroy();
        }
    }
}