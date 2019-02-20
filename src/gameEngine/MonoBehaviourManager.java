package gameEngine;

import java.util.ArrayList;

public class MonoBehaviourManager {

    //List of MonoBehaviour classes
    private static ArrayList<MonoBehaviour> behaviours = new ArrayList<MonoBehaviour>();

    //Adds MonoBehaviour class to list
    public static void addBehaviour(MonoBehaviour behaviour)
    {
        behaviours.add(behaviour);
    }

    //Calls Start method in every MonoBehaviour class in list
    public void CallStart()
    {
        for (MonoBehaviour behaviour : behaviours) {
            behaviour.Start();
        }
    }

    //Calls Update method in every MonoBehaviour class in list
    public static void CallUpdates()
    {
        for (MonoBehaviour behaviour : behaviours) {
            behaviour.Update();
        }
    }
}
