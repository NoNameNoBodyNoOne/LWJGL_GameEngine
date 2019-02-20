package gameEngine.renderEngine.Materials;

import gameEngine.EngineManager;
import gameEngine.renderEngine.DirectionalLight;
import gameEngine.renderEngine.Shader;
import gameEngine.renderEngine.Texture;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

public class DefaultMaterial implements Material{

    public static DirectionalLight SunLight = new DirectionalLight();
    private Shader shader;
    private Texture texture;
    private float glossiness = 0;
    private float shineDamper = 50;
    private Vector4f color = new Vector4f(1,1,1,1);
    public boolean cullOff = false;

    public DefaultMaterial(Shader shader, Texture texture) {
        this.shader = shader;
        this.texture = texture;
    }

    public DefaultMaterial(Texture texture) {
        this.shader = new Shader(EngineManager.properties.getProperty("shader_folder_path") + "DefaultShader");
        this.texture = texture;
    }

    public DefaultMaterial() {
        this.shader = new Shader(EngineManager.properties.getProperty("shader_folder_path") + "DefaultShader");
        this.texture = new Texture();
    }

    @Override
    public void bind(Matrix4f transformMatrix){
        bind();
        shader.setUniform("transform_matrix", transformMatrix);
    }

    @Override
    public void bind() {
        if(cullOff){
            GL11.glDisable(GL11.GL_CULL_FACE);
        }
        shader.bind();
        texture.bind(0);
        shader.setUniform("sampler", 0);
        shader.setUniform("view_matrix", EngineManager.MainCamera.transform.getTransformMatrix());
        shader.setUniform("projection_matrix", EngineManager.MainCamera.getProjectionMatrix());
        shader.setUniform("color", color);
        shader.setUniform("light_dir", SunLight.getLightDir());
        shader.setUniform("light_color", SunLight.getLightColor());
        shader.setUniform("ambient", SunLight.getAmbient());
        shader.setUniform("light_intensity", SunLight.getIntensity());
        shader.setUniform("glossiness", glossiness);
        shader.setUniform("shine_damper", shineDamper);
    }

    @Override
    public void unbind() {
        if(cullOff){
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glCullFace(GL11.GL_BACK);
        }
        texture.unbind();
    }

    public void setTransformMatrix(Matrix4f transformMatrix){
        shader.setUniform("transform_matrix", transformMatrix);
    }

    public float getGlossiness() {
        return glossiness;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public Vector4f getColor() {
        return color;
    }

    public void setGlossiness(float glossiness) {
        this.glossiness = glossiness;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public void setColor(Vector4f color) {
        this.color = color;
    }
}
