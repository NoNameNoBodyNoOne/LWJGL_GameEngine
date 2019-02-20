package gameEngine.renderEngine;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class TransformForCamera extends Transform {

    @Override
    protected void updateMatrix()
    {
        this.transformMatrix = new Matrix4f().identity()
                .rotate(new Quaternionf(this.rotation).invert())
                .translate(new Vector3f(this.position).negate());
    }
}
