package gameEngine.renderEngine.Meshes;

import java.io.Serializable;

public class MeshData {

    /**
     * VNTI stands for:
     * VERTICES NORMALS TEXTRURE_UVS INDICES
     */
    public static class VNTI implements Serializable {
        public float[] vertices = new float[0];
        public float[] uvs = new float[0];
        public float[] normals = new float[0];
        public int[] indices = new int[0];
    }

    /**
     * VNI stands for:
     * VERTICES NORMALS INDICES
     */
    public static class VNI implements Serializable {
        public float[] vertices = new float[0];
        public float[] normals = new float[0];
        public int[] indices = new int[0];
    }

    /**
     * VTI stands for:
     * VERTICES TEXTRURE_UVS INDICES
     */
    public static class VTI implements Serializable {
        public float[] vertices = new float[0];
        public float[] uvs = new float[0];
        public int[] indices = new int[0];
    }

    /**
     * VI stands for:
     * VERTICES INDICES
     */
    public static class VI implements Serializable {
        public float[] vertices = new float[0];
        public int[] indices = new int[0];
    }

}
