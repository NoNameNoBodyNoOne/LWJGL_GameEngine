package gameEngine.renderEngine;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Camera {

    public TransformForCamera transform = new TransformForCamera();

    private float fov;
    private float aspect;
    private float nearZ;
    private float farZ;

    private Matrix4f projectionMatrix;

    private void setProjectionMatrix()
    {
        if(null == projectionMatrix)
            projectionMatrix = new Matrix4f();
        projectionMatrix.setPerspective(fov,aspect,nearZ,farZ);
    }

    public Camera(Vector3f position, Quaternionf rotation, float fov, float aspect, float nearZ, float farZ) {
        this.fov = fov;
        this.aspect = aspect;
        this.nearZ = nearZ;
        this.farZ = farZ;
        setProjectionMatrix();
    }

    public Camera(float fov, float aspect, float nearZ, float farZ) {
        this.fov = fov;
        this.aspect = aspect;
        this.nearZ = nearZ;
        this.farZ = farZ;
        setProjectionMatrix();
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public float getFov() {
        return fov;
    }

    public float getAspect() {
        return aspect;
    }

    public float getNearZ() {
        return nearZ;
    }

    public float getFarZ() {
        return farZ;
    }

    public void setFov(float fov) {
        this.fov = fov;
        setProjectionMatrix();
    }

    public void setAspect(float aspect) {
        this.aspect = aspect;
        setProjectionMatrix();
    }

    public void setNearZ(float nearZ) {
        this.nearZ = nearZ;
        setProjectionMatrix();
    }

    public void setFarZ(float farZ) {
        this.farZ = farZ;
        setProjectionMatrix();
    }
}
