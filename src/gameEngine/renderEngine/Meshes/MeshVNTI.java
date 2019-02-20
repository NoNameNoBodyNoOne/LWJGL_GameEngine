package gameEngine.renderEngine.Meshes;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

/**
 *Mesh with VERTICES, NORMALS, TEXTURE_UVS, INDICES
 * Constructor load data
 */
public class MeshVNTI implements Mesh{

    private int drawCount;
    private int v_id;
    private int n_id;
    private int t_id;
    private int i_id;

    public MeshVNTI(float[] vertices, float[] tex_coords, float[] normals, int[] indices)
    {
        drawCount = indices.length;

        v_id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, v_id);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices), GL_STATIC_DRAW);

        t_id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, t_id);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(tex_coords), GL_STATIC_DRAW);

        n_id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, n_id);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(normals), GL_STATIC_DRAW);

        i_id = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, createBuffer(indices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    private IntBuffer createBuffer(int[] data)
    {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();

        return buffer;
    }

    private FloatBuffer createBuffer(float[] data)
    {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();

        return buffer;
    }

    public void bind() {
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glBindBuffer(GL_ARRAY_BUFFER, v_id);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, t_id);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, n_id);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);
    }

    public void render(){
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, i_id);
        glDrawElements(GL_TRIANGLES, drawCount, GL_UNSIGNED_INT, 0);
    }

    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
    }

}
