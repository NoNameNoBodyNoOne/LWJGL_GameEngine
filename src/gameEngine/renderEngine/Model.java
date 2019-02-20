package gameEngine.renderEngine;

import gameEngine.renderEngine.Materials.Material;
import gameEngine.renderEngine.Meshes.Mesh;
import gameEngine.renderEngine.Meshes.MeshVNTI;

public class Model {

    public Transform transform;
    private Mesh mesh;
    private Material material;

    public Model(Mesh mesh, Material material) {
        this.transform = new Transform();
        this.mesh = mesh;
        this.material = material;
    }

    public Model(Transform transform,Mesh  mesh, Material material) {
        this.transform = transform;
        this.mesh = mesh;
        this.material = material;
    }

    public Model(float[] vertices, float[] tex_coords, float[] normals, int[] indices, Material material) {
        this.transform = new Transform();
        this.mesh = new MeshVNTI(vertices, tex_coords, normals, indices);
        this.material = material;
    }

    public void render() {
        mesh.bind();
        material.bind(transform.transformMatrix);
        mesh.render();
        material.unbind();
        mesh.unbind();
    }
}
