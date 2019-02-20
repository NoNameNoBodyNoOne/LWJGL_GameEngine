package gameEngine;

public class Time {

    private static long lastTime;

    public void saveTime(){
        lastTime =  System.nanoTime();
    }

    public static long time(){
        return System.nanoTime();
    }

    public static long deltaTime(){
        return (System.nanoTime() - lastTime);
    }

    public static double timeToSeconds(long time){
        return ((double)time)/1000000000.0;
    }

}
