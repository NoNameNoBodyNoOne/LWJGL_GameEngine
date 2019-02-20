package gameEngine.renderEngine;

import org.joml.Vector3f;
import org.joml.Vector4f;

public class DirectionalLight {

    private Vector3f lightDir;
    private Vector4f lightColor;
    private float ambient;
    private float intensity;

    public DirectionalLight(){
        this.lightColor = new Vector4f(0,0,0,1);
        this.lightDir = new Vector3f(0,1,0);
        this.ambient = 0.3f;
        this.intensity = 1f;
    }

    public DirectionalLight(Vector3f lightDir, Vector4f lightColor) {
        this.lightDir = lightDir;
        this.lightColor = lightColor;
        this.ambient = 0.3f;
        this.intensity = 1f;
    }

    public DirectionalLight(Vector3f lightDir, Vector4f lightColor, float ambient) {
        this.lightDir = lightDir;
        this.lightColor = lightColor;
        this.ambient = ambient;
        this.intensity = 1f;
    }

    public DirectionalLight(Vector3f lightDir, Vector4f lightColor, float ambient, float intensity) {
        this.lightDir = lightDir;
        this.lightColor = lightColor;
        this.ambient = ambient;
        this.intensity = intensity;
    }

    public Vector3f getLightDir() {
        return lightDir;
    }

    public void setLightDir(Vector3f lightDir) {
        this.lightDir = lightDir;
    }

    public Vector4f getLightColor() {
        return lightColor;
    }

    public void setLightColor(Vector4f lightColor) {
        this.lightColor = lightColor;
    }

    public float getAmbient() {
        return ambient;
    }

    public void setAmbient(float ambient) {
        this.ambient = ambient;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }
}
