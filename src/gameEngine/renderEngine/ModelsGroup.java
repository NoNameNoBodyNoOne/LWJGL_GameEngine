package gameEngine.renderEngine;

import gameEngine.renderEngine.Materials.Material;
import gameEngine.renderEngine.Meshes.Mesh;

import java.util.ArrayList;
import java.util.Arrays;

public class ModelsGroup {

    private ArrayList<Transform> transforms;
    private Mesh mesh;
    private Material material;

    public ModelsGroup(ArrayList<Transform> transforms, Mesh mesh, Material material) {
        this.transforms = transforms;
        this.mesh = mesh;
        this.material = material;
    }

    public ModelsGroup(Transform[] transforms, Mesh mesh, Material material) {
        this.transforms = new ArrayList<>(Arrays.asList(transforms));
        this.mesh = mesh;
        this.material = material;
    }

    public void addTransform(Transform transform) {
        transforms.add(transform);
    }

    public void removeTransform(int i) {
        transforms.remove(i);
    }

    public Transform getTransform(int i) {
        return transforms.get(i);
    }

    public void setTransform(int i, Transform transform) {
        transforms.set(i, transform);
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void render() {
        mesh.bind();
        material.bind();
        for(Transform transform : transforms) {
            material.setTransformMatrix(transform.transformMatrix);
            mesh.render();
        }
        material.unbind();
        mesh.unbind();
    }
}
