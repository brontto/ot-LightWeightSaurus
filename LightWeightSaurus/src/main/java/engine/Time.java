package engine;

public class Time {

    private static double lastLoopTime;
    private static double firstTime;

    /**
     * Alustaa ajan.
     */
    public static void init() {
        lastLoopTime = getTime();
        firstTime = lastLoopTime;
    }

    public static double getTime() {
        return System.nanoTime() / 1000_000_000.0;
    }

    /**
     * Kertoo päivityksestä kuluneen ajan.
     * @return Kulunut aika edellisestä päivityksestä.
     */
    public static float getDeltaTime() {
        double time = getTime();
        float elapsedTime = (float) (time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
    }

    public static double getLastLoopTime() {
        return lastLoopTime;
    }

    /**
     * Kertoo käynnistyksestä kuluneen ajan.
     * @return käynnistyksestä kulunut aika.
     */
    public static double getElapsedTime() {
        return getTime() - firstTime;
    }
}
