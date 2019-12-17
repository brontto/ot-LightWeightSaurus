package graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {

    private static final Vector3f DEFAULT_COLOR = new Vector3f(1.0f, 1.0f, 1.0f);

    private final int vaoId;
    private final int posVboId;
    private final int tcVboId;
    private final int idxVboId;
    private final int vnVboId;

    private Vector3f color;
    private Texture texture;

    private final int vertexCount;

    /**
     * Luo ja alustaa Meshin datan mukaan.
     * @param vertices Meshin verteksit.
     * @param textCoords Tekstuurin koordinaatit.
     * @param normals Lista Normalseja.
     * @param indices Indexi lista joka määrää missä järjestyksessä verteksit piirretään.
     */
    public Mesh(float[] vertices, float[] textCoords, float[] normals, int[] indices) {
        FloatBuffer posBuffer = null;
        FloatBuffer textCoordsBuffer = null;
        FloatBuffer vecNormalsBuffer = null;
        IntBuffer indicesBuffer = null;

        try {
            color = DEFAULT_COLOR;
            vertexCount = indices.length;

            vaoId = glGenVertexArrays();
            glBindVertexArray(vaoId);

            //position VBO
            posVboId = glGenBuffers();
            posBuffer = MemoryUtil.memAllocFloat(vertices.length);
            posBuffer.put(vertices).flip();

            glBindBuffer(GL_ARRAY_BUFFER, posVboId);
            glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            //color VBO
            vnVboId = glGenBuffers();
            vecNormalsBuffer = MemoryUtil.memAllocFloat(normals.length);
            vecNormalsBuffer.put(normals).flip();
            glBindBuffer(GL_ARRAY_BUFFER, vnVboId);
            glBufferData(GL_ARRAY_BUFFER, vecNormalsBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(2,3,GL_FLOAT, false, 0, 0);

            //texture VBO
            tcVboId = glGenBuffers();
            textCoordsBuffer = MemoryUtil.memAllocFloat(textCoords.length);
            textCoordsBuffer.put(textCoords).flip();
            glBindBuffer(GL_ARRAY_BUFFER, tcVboId);
            glBufferData(GL_ARRAY_BUFFER, textCoordsBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

            //Index VBO
            idxVboId = glGenBuffers();
            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);

        } finally {
            if (posBuffer != null) {
                MemoryUtil.memFree(posBuffer);
            }
            if (textCoordsBuffer != null) {
                MemoryUtil.memFree(textCoordsBuffer);
            }
            if (indicesBuffer != null) {
                MemoryUtil.memFree(indicesBuffer);
            }
        }
    }

    /**
     * Piirtää meshin ruudulle.
     */
    public void render() {
        if(texture != null) {
            glActiveTexture(GL_TEXTURE0);

            glBindTexture(GL_TEXTURE_2D, texture.getId());
        }
        //Draw mesh
        glBindVertexArray(getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, getIdxVboId());
        glDrawElements(GL_TRIANGLES, getVertexCount(), GL_UNSIGNED_INT, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);
    }

    /**
     * Vapauttaa Meshin käyttämän resurssit kun niitä ei enää tarvita.
     */
    public void destroy() {
        glDisableVertexAttribArray(0);

        //Delete VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(posVboId);
        glDeleteBuffers(tcVboId);
        glDeleteBuffers(idxVboId);

        if(texture != null) {
            texture.destroy();
        }
        //Delete VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }

    public void setColor(Vector3f colour) {
        this.color = colour;
    }

    public Vector3f getColor() {
        return this.color;
    }

    public boolean isTextured() {
        return this.texture != null;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getPosVboId() {
        return posVboId;
    }

    public int getTcVboId() {
        return tcVboId;
    }

    public int getIdxVboId() {
        return idxVboId;
    }
}