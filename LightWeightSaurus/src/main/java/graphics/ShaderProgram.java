package graphics;


import org.lwjgl.opengl.GL20;
import utils.FileUtils;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
    private final int programID;

    private int vertexShaderID, fragmentShaderID;

    public ShaderProgram() throws Exception {
        programID = glCreateProgram();
        if(programID == 0){
            throw  new Exception("Could not create Shader");
        }
    }

    public void createVertexShader(String path) throws Exception {
        vertexShaderID = createShader(FileUtils.loadAsString(path), GL_VERTEX_SHADER);
    }

    public void createFragmentShader(String path) throws Exception {
        fragmentShaderID = createShader(FileUtils.loadAsString(path), GL_FRAGMENT_SHADER);
    }

    public int createShader(String shaderCode, int shaderType) throws Exception {
        int shaderId = glCreateShader(shaderType);
        if(shaderId == 0){
            throw new Exception("Error creationg shader. Type: " + shaderType);
        }

        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);

        if(glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0){
            throw new Exception("Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024));
        }

        glAttachShader(programID, shaderId);

        return shaderId;
    }

    public void link() throws Exception {
        glLinkProgram(programID);
        if(glGetProgrami(programID, GL_VALIDATE_STATUS) == 0) {
            throw new Exception("Error linking Shader code: " + glGetProgramInfoLog(programID, 1024));
        }

        if(vertexShaderID != 0) {
            glDetachShader(programID, vertexShaderID);
        }

        if(fragmentShaderID != 0) {
            glDetachShader(programID, fragmentShaderID);
        }

        glValidateProgram(programID);
        if(glGetProgrami(programID, GL_VALIDATE_STATUS) == 0) {
            System.out.println("Waringn validating Shader code: " + glGetShaderInfoLog(programID, 1024));
        }
    }

    public void bind() {
        glUseProgram(programID);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void destroy() {
        unbind();
        if(programID != 0) {
            glDeleteProgram(programID);
        }
    }
}