package gameEngine.renderEngine;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

/**
 * Shader class is responsible for creating shader from file and managing it.
 * @author Michał Warzecha
 */
public class Shader {
    /**
     * Stores shader program id
     */
    private int program;
    /**
     * Stores vertex shader id
     */
    private int vs;
    /**
     * Stores fragment shader id
     */
    private int fs;

    /**
     * Creates vertex shader from file
     * @param filepath path to shader file (with file extension)
     * @return id of created vertex shader
     */
    private static int createVertexShaderFromFile(String filepath){
        int vs = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vs, readFile(filepath));
        glCompileShader(vs);
        if(glGetShaderi(vs, GL_COMPILE_STATUS) != 1) {
            System.err.println(glGetShaderInfoLog(vs));
            System.exit(1);
        }
        return vs;
    }

    /**
     * Creates fragment shader from file
     * @param filepath path to shader file (with file extension)
     * @return id of created fragment shader
     */
    private static int createFragmentShaderFromFile(String filepath){
        int fs = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fs, readFile(filepath));
        glCompileShader(fs);
        if(glGetShaderi(fs, GL_COMPILE_STATUS) != 1) {
            System.err.println(glGetShaderInfoLog(fs));
            System.exit(1);
        }
        return fs;
    }

    /**
     * Links and validates shader program
     * @param program shader program id
     */
    private static void linkAndValidateProgram(int program){
        glLinkProgram(program);
        if(glGetProgrami(program, GL_LINK_STATUS) != 1) {
            System.err.println(glGetProgramInfoLog(program));
            System.exit(1);
        }
        glValidateProgram(program);
        if(glGetProgrami(program, GL_VALIDATE_STATUS) != 1) {
            System.err.println(glGetProgramInfoLog(program));
            System.exit(1);
        }
    }

    /**
     * Creates instance of shader and stores its ids
     * @param vertexShaderPath path to vertex shader path (with file extension)
     * @param fragmentShaderPath path to fragment shader path (with file extension)
     */
    public Shader(String vertexShaderPath, String fragmentShaderPath){
        program = glCreateProgram();
        vs = createVertexShaderFromFile(vertexShaderPath);
        fs = createFragmentShaderFromFile(fragmentShaderPath);
        glAttachShader(program, vs);
        glAttachShader(program, fs);

        glBindAttribLocation(program, 0, "vertices");
        glBindAttribLocation(program, 1, "textures");
        glBindAttribLocation(program, 2, "normals");

        linkAndValidateProgram(program);
    }

    /**
     * Creates instance of shader and stores its ids
     * @param shaderPath path to shader files (without extension,
     *                   vertex and fragment shader file names has to be the same
     *                   and have extensions accordingly ".vs" and ".fs"!)
     */
    public Shader(String shaderPath){
        this(shaderPath + ".vs", shaderPath + ".fs");
    }

    /**
     * Sets shader's uniform variable
     * @param name name of uniform variable
     * @param value int value to set
     */
    public void setUniform(String name, int value){
        int location = glGetUniformLocation(program, name);
        if(location != -1) {
            glUniform1i(location, value);
        }
    }

    /**
     * Sets shader's uniform variable
     * @param name name of uniform variable
     * @param value float value to set
     */
    public void setUniform(String name, float value){
        int location = glGetUniformLocation(program, name);
        if(location != -1) {
            glUniform1f(location, value);
        }
    }

    /**
     * Sets shader's uniform variable
     * @param name name of uniform variable
     * @param value Vector3f value to set
     */
    public void setUniform(String name, Vector3f value){
        int location = glGetUniformLocation(program, name);
        if(location != -1) {
            glUniform3f(location, value.x, value.y, value.z);
        }
    }

    /**
     * Sets shader's uniform variable
     * @param name name of uniform variable
     * @param value Vector4f value to set
     */
    public void setUniform(String name, Vector4f value){
        int location = glGetUniformLocation(program, name);
        if(location != -1) {
            glUniform4f(location, value.x, value.y, value.z, value.w);
        }
    }

    /**
     * Sets shader's uniform variable
     * @param name name of uniform variable
     * @param value Matrix4f value to set
     */
    public void setUniform(String name, Matrix4f value)
    {
        int location = glGetUniformLocation(program, name);
        if(location != -1) {
            FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
            value.get(buffer);
            glUniformMatrix4fv(location, false, buffer);
        }
    }

    /**
     * Binds shader program (use before rendering {@link gameEngine.renderEngine.Meshes.Mesh Mesh})
     */
    public void bind(){
        glUseProgram(program);
    }

    /**
     * Reads file to String
     * @param filepath path to file
     * @return String containing file content
     * TODO: Move readFile method to another class
     */
    private static String readFile(String filepath){
        StringBuilder string = new StringBuilder();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(new File(filepath)));
            String line;
            while((line = br.readLine()) != null)
            {
                string.append(line);
                string.append("\n");
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return string.toString();
    }
}
