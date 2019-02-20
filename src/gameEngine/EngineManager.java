package gameEngine;

import gameEngine.Environment.Skybox;
import gameEngine.renderEngine.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Main Engine Class which runs whole application
 * @author Michał Warzecha
 */
public class EngineManager {
    /**
     * Calls Start and Update functions in every {@link MonoBehaviour} class
     */
    private static MonoBehaviourManager behaviourManager = new MonoBehaviourManager();
    /**
     * Creates Display
     */
    private static DisplayManager display = new DisplayManager();
    /**
     * Handles Input
     */
    private static Input input = new Input();
    /**
     * Handles Time
     */
    private static Time time = new Time();
    /**
     * Stores game's Skybox
     */
    public static Skybox skybox = new Skybox();
    /**
     * Main Camera
     */
    public static Camera MainCamera = new Camera((float) Math.toRadians(60), 16f/9f, 0.01f, 1000f);
    /**
     * Stores application's window id
     */
    public static long window;
    /**
     * Stores application's configuration properties
     */
    public static Properties properties = new Properties();
    /**
     * Name of configuration file
     */
    private static final String fileName = "app.config";

    /**
     * Loads properties from file
     */
    private static void LoadProperties() {
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
            properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that starts Application
     * (contains main application loop)
     */
    public static void runApplication() {
        LoadProperties();

        display.createDisplay();                    //Creating display
        window = display.window;
        input.initializeInput(display.window);      //Initializing handling inputs

        glEnable(GL_TEXTURE_2D);                    //Enabling texture handling in OpenGl

        time.saveTime();                            //Saves time moment to be able to calculate delta time

        skybox.init();

        behaviourManager.CallStart();               //Calling Start method in every MonoBehaviour class

        //Main application loop
        while ( !glfwWindowShouldClose(display.window) ) {
            glfwPollEvents();                       //Pooling events

            display.updateDisplay();                //Updating display

            input.updateInput();                    //Updating inputs

            time.saveTime();                        //Saves time moment to be able to calculate delta time
        }

        display.closeDisplay();                     //Close display after exiting main loop
    }

    /**
     * Adds {@link MonoBehaviour} class to application's MonoBehaviours list
     * (Allows application to call {@link MonoBehaviour#Start()} and {@link MonoBehaviour#Update()} methods)
     * @param behaviour your {@link MonoBehaviour} class instance
     */
    public static void addBehaviourToApplication(MonoBehaviour behaviour){
        MonoBehaviourManager.addBehaviour(behaviour);
    }
}
