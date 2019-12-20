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
    private final int vaoId;
    private final int posVboId;
    private final int tcVboId;
    private final int idxVboId;


    private final int vertexCount;

    private final Texture texture;

    /**
     * Luo ja alustaa Meshin datan mukaan.
     *
     * @param vertices   Meshin verteksit.
     * @param textCoords Tekstuurin koordinaatit.
     * @param indices    Indexi lista joka määrää missä järjestyksessä verteksit piirretään.
     * @param texture    Tekstuuri objekti.
     */
    public Mesh(float[] vertices, float[] textCoords, int[] indices, Texture texture) {
        FloatBuffer posBuffer = null;
        FloatBuffer textCoordsBuffer = null;
        IntBuffer indicesBuffer = null;

        try {
            vertexCount = indices.length;
            this.texture = texture;

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
        glActiveTexture(GL_TEXTURE0);

        glBindTexture(GL_TEXTURE_2D, texture.getId());

        //Draw mesh
        glBindVertexArray(getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, getIdxVboId());
        glDrawElements(GL_TRIANGLES, getVertexCount(), GL_UNSIGNED_INT, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
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

        texture.destroy();

        //Delete VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
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