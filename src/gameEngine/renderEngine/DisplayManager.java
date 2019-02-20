package gameEngine.renderEngine;

import gameEngine.EngineManager;
import gameEngine.Environment.Skybox;
import gameEngine.MonoBehaviourManager;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class DisplayManager {

    public final int WIDTH = 1920;
    public final int HEIGHT = 1800;

    public long window;

    public void createDisplay()
    {
        GLFWErrorCallback.createPrint(System.err).set();

        if( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW.");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

                                                                  //NULL -if window mode
                                                                  //glfwGetPrimaryMonitor() -if fullscreen mode
        window = glfwCreateWindow(WIDTH, HEIGHT, "New Window", glfwGetPrimaryMonitor(), NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        glfwMakeContextCurrent(window);

        glfwShowWindow(window);

        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
    }
    public void updateDisplay()
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glClearColor(135f/255f,206f/255f, 235f/255f, 1);

        EngineManager.skybox.render();

        MonoBehaviourManager.CallUpdates();

        glfwSwapBuffers(window);
    }
    public void closeDisplay()
    {
        glfwTerminate();
    }
}
