package gameEngine.inputHandlers;

import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.HashSet;

import static org.lwjgl.glfw.GLFW.*;

public class KeyHandler extends GLFWKeyCallback{

    public static boolean[] prevKeys = new boolean[65536];
    public static boolean[] keys = new boolean[65536];

    public static HashSet<Integer> keyCodesToUpdate = new HashSet<Integer>();
    // The GLFWKeyCallback class is an abstract method that
    // can't be instantiated by itself and must instead be extended
    //
    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        // TODO Auto-generated method stub
        keyCodesToUpdate.add(key);
        keys[key] = action != GLFW_RELEASE;
    }

    public void updatePrevKeys()
    {
        for (int key: keyCodesToUpdate) {
            prevKeys[key] = keys[key];
        }
        keyCodesToUpdate.clear();
    }

    //Boolean method that returns true if a given key is just pressed down.
    public static boolean getKeyDown(int keycode) {
        return (keys[keycode] && !prevKeys[keycode]);
    }
    //Boolean method that returns true if a given key is hold down.
    public static boolean getKey(int keycode) {
        return keys[keycode];
    }
    //Boolean method that returns true if a given key is just released.
    public static boolean getKeyUp(int keycode) {
        return (!keys[keycode] && prevKeys[keycode]);
    }

}