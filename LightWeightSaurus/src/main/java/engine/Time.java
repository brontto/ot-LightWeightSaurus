package engine;

public class Time {

    private static double lastLoopTime;

    public static void init() {
        lastLoopTime = getTime();
    }

    public static double getTime() {
        return System.nanoTime() / 1000_000_000.0;
    }

    public static float getElapsedTime() {
        double time = getTime();
        float elapsedTime = (float) (time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
    }

    public static double getLastLoopTime() {
        return lastLoopTime;
    }
}
