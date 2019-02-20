package gameEngine.inputHandlers;

import org.lwjgl.glfw.*;

import java.util.HashSet;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseInputHandler extends GLFWMouseButtonCallback{

    private static boolean[] buttons = new boolean[8];
    private static boolean[] prevButtons = new boolean[8];

    public static HashSet<Integer> buttonsToUpdate = new HashSet<Integer>();

    @Override
    public void invoke(long window, int button, int action, int mods) {
        buttonsToUpdate.add(button);
        buttons[button] = action != GLFW_RELEASE;
    }


    public void updatePrevKeys()
    {
        for (int key: buttonsToUpdate) {
            prevButtons[key] = buttons[key];
        }
        buttonsToUpdate.clear();
    }

    // boolean method that returns true if a given key
    // is pressed.
    public static boolean getButtonDown(int keycode) {
        return (buttons[keycode] && !prevButtons[keycode]);
    }
    public static boolean getButton(int keycode) {
        return buttons[keycode];
    }
    public static boolean getButtonUp(int keycode) {
        return (!buttons[keycode] && prevButtons[keycode]);
    }
}
