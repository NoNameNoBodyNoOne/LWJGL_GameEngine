package gameEngine.inputHandlers;

public class MouseHandler {
    public static MouseInputHandler Buttons;
    public static MousePositionHandler Position;

    public MouseHandler()
    {
        Buttons = new MouseInputHandler();
        Position = new MousePositionHandler();
    }
}
