package gameEngine;

import gameEngine.inputHandlers.*;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

    public static KeyHandler Keys;          //Handles Keyboard Input
    public static MouseHandler Mouse;       //Handles Mouse Input and Position

    public Input()
    {
        Keys = new KeyHandler();
        Mouse = new MouseHandler();
    }

    public void initializeInput(long window)
    {
        glfwSetKeyCallback(window, Keys);
        glfwSetCursorPosCallback(window, Mouse.Position);
        glfwSetMouseButtonCallback(window, Mouse.Buttons);
    }

    public void updateInput()
    {
        Keys.updatePrevKeys();
        Mouse.Buttons.updatePrevKeys();
        Mouse.Position.updatePrev();
    }
}
