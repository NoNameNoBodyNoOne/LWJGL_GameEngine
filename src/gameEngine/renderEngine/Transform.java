package gameEngine.renderEngine;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform {

    protected Matrix4f transformMatrix;

    protected Vector3f position, scale;
    protected Quaternionf rotation;

    public Transform()
    {
        this.transformMatrix = new Matrix4f().scale(1,1,1);
        this.position = new Vector3f(0,0,0);
        this.scale = new Vector3f(1,1,1);
        this.rotation = new Quaternionf().identity();
        updateMatrix();
    }

    public Transform(Vector3f position, Vector3f rotation, Vector3f scale)
    {
        this.position = position;
        this.rotation = new Quaternionf().rotationXYZ(rotation.x, rotation.y, rotation.z);
        this.scale = scale;
        updateMatrix();
    }

    public Transform(Vector3f position, Quaternionf rotation, Vector3f scale)
    {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        updateMatrix();
    }

    public Transform(Vector3f position, Quaternionf rotation)
    {
        this.position = position;
        this.rotation = rotation;
        this.scale = new Vector3f(1,1,1);
        updateMatrix();
    }

    public Transform(Vector3f position)
    {
        this.position = position;
        this.rotation = new Quaternionf().identity();
        this.scale = new Vector3f(1,1,1);
        updateMatrix();
    }

    protected void updateMatrix()
    {
        this.transformMatrix = new Matrix4f().identity()
                .translate(this.position)
                .rotate(this.rotation)
                .scale(this.scale);
    }

    public Matrix4f getTransformMatrix() {
        return transformMatrix;
    }

    public void setTransformMatrix(Matrix4f transformMatrix) {
        this.transformMatrix = transformMatrix;
    }

    public Vector3f getPosition(){
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
        updateMatrix();
    }

    public Vector3f forward(){
        float x = 2 * (rotation.x*rotation.z + rotation.w*rotation.y);
        float y = 2 * (rotation.y*rotation.z - rotation.w*rotation.x);
        float z = 1 - 2 * (rotation.x*rotation.x + rotation.y*rotation.y);
        return new Vector3f(x,y,z);
    }

    public Vector3f up(){
        float x = 2 * (rotation.x*rotation.y - rotation.w*rotation.z);
        float y = 1 - 2 * (rotation.x*rotation.x + rotation.z*rotation.z);
        float z = 2 * (rotation.y*rotation.z + rotation.w*rotation.x);
        return new Vector3f(x,y,z);
    }

    public Vector3f left(){
        float x = 1 - 2 * (rotation.y*rotation.y + rotation.z*rotation.z);
        float y = 2 * (rotation.x*rotation.y + rotation.w*rotation.z);
        float z = 2 * (rotation.x*rotation.z - rotation.w*rotation.y);
        return new Vector3f(x,y,z);
    }

    public Vector3f getScale() {
        return scale;
    }

    public Quaternionf getRotation() {
        return rotation;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
        updateMatrix();
    }

    public void setRotation(Quaternionf rotation) {
        this.rotation = rotation;
        updateMatrix();
    }

    public void translate(Vector3f vector) {
        this.position.add(vector);
        updateMatrix();
    }

    public void rotate(float angle, Vector3f axis){
        this.rotation.rotateAxis(angle, axis.rotate(new Quaternionf(this.rotation).invert()));
        updateMatrix();
    }
}
