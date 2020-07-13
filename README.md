# LWJGL_GameEngine
Java OpenGL GameEngine

To create script that executes in game create class that implements MonoBehaviour interface.
Start method will be executed when game starts. Update method will be executed every frame.

For example:

```Java
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
```

To attach created scripts to application you have to add them calling method EngineManager.addBehaviourToApplication() before start of the application.

For example:

```Java
public class Main {

  public static void main(final String[] args) {
    EngineManager.addBehaviourToApplication(new ExampleScript());
    EngineManager.runApplication();
  }
}
```

</br>
</br>

<h3>Example screenshots:</h3>

![alt text](https://github.com/NoNameNoBodyNoOne/LWJGL_GameEngine/blob/master/Screenshots/javaEngine1.png)

![alt text](https://github.com/NoNameNoBodyNoOne/LWJGL_GameEngine/blob/master/Screenshots/javaEngine2.png)
