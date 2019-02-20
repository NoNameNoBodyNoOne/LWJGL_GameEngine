package gameEngine.renderEngine.Materials;

import org.joml.Matrix4f;

public interface Material {

    void bind(Matrix4f transformMatrix);
    void bind();
    void setTransformMatrix(Matrix4f transformMatrix);
    void unbind();
}
