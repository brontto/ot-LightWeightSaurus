package graphics;


import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;
import utils.FileUtils;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
    private final int programID;

    private int vertexShaderID, fragmentShaderID;

    private final Map<String, Integer> uniforms;

    /**
     * Luo ja alustaa programin.
     */
    public ShaderProgram() throws Exception {
        programID = glCreateProgram();
        if (programID == 0) {
            throw new Exception("Could not create Shader");
        }
        uniforms = new HashMap<>();
    }

    /**
     * Luo verteksi shaderin.
     *
     * @param path Shaderin koodin sijainti.
     */
    public void createVertexShader(String path) throws Exception {
        vertexShaderID = createShader(FileUtils.loadAsString(path), GL_VERTEX_SHADER);
    }

    /**
     * Luo fragment shaderin.
     *
     * @param path Shaderin koodin sijainti.
     */
    public void createFragmentShader(String path) throws Exception {
        fragmentShaderID = createShader(FileUtils.loadAsString(path), GL_FRAGMENT_SHADER);
    }

    /**
     * Luo shaderin ja yhdistää sen programiin.
     *
     * @param shaderCode Shaderin koodin.
     * @param shaderType Shaderin tyyppi.
     * @return Shaderin id.
     */
    public int createShader(String shaderCode, int shaderType) throws Exception {
        int shaderId = glCreateShader(shaderType);
        if (shaderId == 0) {
            throw new Exception("Error creationg shader. Type: " + shaderType);
        }

        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new Exception("Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024));
        }

        glAttachShader(programID, shaderId);

        return shaderId;
    }

    /**
     * Linkkaa shaderit programiin.
     */
    public void link() throws Exception {
        glLinkProgram(programID);
        if (glGetProgrami(programID, GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking Shader code: " + glGetProgramInfoLog(programID, 1024));
        }

        if (vertexShaderID != 0) {
            glDetachShader(programID, vertexShaderID);
        }

        if (fragmentShaderID != 0) {
            glDetachShader(programID, fragmentShaderID);
        }

        glValidateProgram(programID);
        if (glGetProgrami(programID, GL_VALIDATE_STATUS) == 0) {
            System.out.println("Waringn validating Shader code: " + glGetShaderInfoLog(programID, 1024));
        }
    }

    /**
     * Luo uniformin ja tallentaa sen tiedot listaan uniforms.
     *
     * @param uniformName Uniformin nimi.
     */
    public void createUniform(String uniformName) throws Exception {
        int uniformLocation = glGetUniformLocation(programID, uniformName);
        if (uniformLocation < 0) {
            throw new Exception("Could not find uniform: " + uniformName);
        }
        uniforms.put(uniformName, uniformLocation);
    }

    /**
     * Asettaa shaderille menevän uniformin joka sisältää matriisin.
     *
     * @param uniformName uniformin nimi.
     * @param value       value.
     */
    public void setUniform(String uniformName, Matrix4f value) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(16);
            value.get(buffer);
            glUniformMatrix4fv(uniforms.get(uniformName), false, buffer);
        }
    }

    /**
     * Asettaa shaderille menevän unfiormin.
     *
     * @param uniformName uniformin nimi.
     * @param value       value.
     */
    public void setUniform(String uniformName, int value) {
        glUniform1i(uniforms.get(uniformName), value);
    }

    /**
     * Yhdistää programin näytönohjaimelle.
     */
    public void bind() {
        glUseProgram(programID);
    }

    /**
     * Irottaa prgramin näytönohjaimelta.
     */
    public void unbind() {
        glUseProgram(0);
    }

    /**
     * Vapauttaa resurssit.
     */
    public void destroy() {
        unbind();
        if (programID != 0) {
            glDeleteProgram(programID);
        }
    }
}