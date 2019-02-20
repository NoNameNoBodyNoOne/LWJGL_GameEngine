package gameEngine.Environment;

import gameEngine.EngineManager;
import gameEngine.MonoBehaviour;
import gameEngine.renderEngine.Meshes.Mesh;
import gameEngine.renderEngine.Meshes.MeshVI;
import gameEngine.renderEngine.Shader;
import org.joml.Vector4f;

import static gameEngine.renderEngine.Materials.DefaultMaterial.SunLight;

public class Skybox {

    private static final float SIZE = 500f;

    private static final float[] VERTICES = {
            -SIZE,  -SIZE, -SIZE,   //0 FRONT BOTTOM LEFT
            -SIZE, -SIZE, SIZE,     //1 FRONT BOTTOM RIGHT
            -SIZE, SIZE, -SIZE,     //2 FRONT TOP LEFT
            -SIZE, SIZE, SIZE,      //3 FRONT TOP RIGHT
            SIZE,  -SIZE, -SIZE,    //4 BACK BOTTOM LEFT
            SIZE, -SIZE, SIZE,      //5 BACK BOTTOM RIGHT
            SIZE, SIZE, -SIZE,      //6 BACK TOP LEFT
            SIZE, SIZE, SIZE,       //7 BACK TOP RIGHT
    };

    private static final int[] INDICES = {
            0, 2, 3, 3, 1, 0,   //FRONT FACE
            0, 4, 6, 6, 2, 0,   //LEFT FACE
            1, 3, 7, 7, 5, 1,   //RIGHT FACE
            0, 1, 5, 5, 4, 0,   //BOTTOM FACE
            2, 6, 7, 7, 3, 2,   //TOP FACE
            4, 5, 7, 7, 6, 4    //BACK FACE
    };

    Shader shader;

    Mesh mesh;
    private Vector4f color = new Vector4f(155f/255f,226f/255f, 255f/255f, 1);

    public void init() {
        mesh = new MeshVI(VERTICES, INDICES);

        shader = new Shader(EngineManager.properties.getProperty("shader_folder_path") + "SkyboxShader");
    }

    public Vector4f getColor() {
        return color;
    }

    public void setColor(Vector4f color) {
        this.color = color;
    }

    public void render() {
        mesh.bind();
        shader.bind();
        shader.setUniform("color", color);
        shader.setUniform("light_dir", SunLight.getLightDir());
        shader.setUniform("light_color", SunLight.getLightColor());
        shader.setUniform("ambient", SunLight.getAmbient());
        shader.setUniform("light_intensity", SunLight.getIntensity());
        shader.setUniform("view_matrix", MonoBehaviour.MainCamera.transform.getTransformMatrix());
        shader.setUniform("projection_matrix", MonoBehaviour.MainCamera.getProjectionMatrix());
        mesh.render();
        mesh.unbind();
    }
}
