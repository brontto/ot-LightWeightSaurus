package graphics;

import engine.Window;
import game.GameItem;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    private static final float FOV = (float) Math.toRadians(60.0f);

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 1000.0f;

    private Matrix4f projectionMatrix;

    private ShaderProgram shaderProgram;

    private final Transformation transformation;

    public Renderer() {
        transformation = new Transformation();
    }

    public void init(Window window) throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader("/shaders/mainVertex.glsl");
        shaderProgram.createFragmentShader("/shaders/mainFragment.glsl");
        shaderProgram.link();

        float aspectRatio = (float) window.getWidth() / window.getHeight();
        projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
        shaderProgram.createUniforms("projectionMatrix");
        shaderProgram.createUniforms("worldMatrix");

        window.setClearColor(0, 0, 0, 0);
    }

    public void render(Window window, GameItem[] gameItems) {
        clear();

        shaderProgram.bind();

        Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV,
                window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        shaderProgram.setUniforms("projectionMatrix", projectionMatrix);

        for (GameItem gameItem : gameItems) {
            Matrix4f worldMatrix =
                    transformation.getWorldMatrix(
                            gameItem.getPosition(),
                            gameItem.getRotation(),
                            gameItem.getScale());
                    shaderProgram.setUniforms("worldMatrix", worldMatrix);

                    gameItem.getMesh().render();
        }



        shaderProgram.unbind();
    }

    private void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void destroy() {
        if (shaderProgram != null) {
            shaderProgram.destroy();
        }
    }
}