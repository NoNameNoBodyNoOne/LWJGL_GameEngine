package gameEngine.inputHandlers;

import com.sun.javafx.geom.Vec2d;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MousePositionHandler extends GLFWCursorPosCallback {

    private static Double prev_xpos = null, prev_ypos = null;
    private static Double xpos = null, ypos = null;

    @Override
    public void invoke(long window, double xpos, double ypos) {
        // TODO Auto-generated method stub
        // this basically just prints out the X and Y coordinates
        // of our mouse whenever it is in our window
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public static Vec2d getMousePos() {
        if(null == xpos || null == ypos)
            return new Vec2d(0,0);
        return new Vec2d(xpos, ypos);
    }

    public static Vec2d getMouseMovement() {
        if(null == xpos || null == ypos || null == prev_xpos || null == prev_ypos)
            return new Vec2d(0,0);
        return new Vec2d(xpos - prev_xpos, ypos - prev_ypos);
    }

    public static void updatePrev() {
        prev_xpos = xpos;
        prev_ypos = ypos;
    }
}
