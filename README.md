# LWJGL_GameEngine
Java OpenGl GameEngine

To create script that executes in game create class that implements MonoBehaviour interface.
Start method will be executed when game starts. Update method will be executed every frame.

For example:

<pre>
public class ExampleScript implements MonoBehaviour{
  @Override
  public void Start() {
    //Execute at the beginning of game
  }
  
  @Override
  public void Update() {
    //Execute every frame
  }
}
</pre>

To attach created scripts to application you have to add them calling method EngineManager.addBehaviourToApplication() before start of the application.

For example:

<pre>
public class Main {

  public static void main(final String[] args) {
    EngineManager.addBehaviourToApplication(new ExampleScript());
    EngineManager.runApplication();
  }
}
</pre>
