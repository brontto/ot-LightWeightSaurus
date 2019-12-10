package engine;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Moottori sisältää kaikki muut osat ja huolehtii niiden pyörittämisestä.
 */
public class Engine {

    public static final int TARGET_FPS = 75;

    public static final int TARGET_UPS = 30;

    public static final int WIDTH = 1280, HEIGHT = 720;

    public Window window;

    private IGameLogic gameLogic;

    private boolean inited;

    private boolean keepRunning;

    /**
     * Konsturktori jolla luodaan moottori.
     *
     * @param windowTitle Titteli joka näkyy ikkunan yläreunassa.
     * @param vSync Laitetaanko Vsync päälle.
     * @param gameLogic Varsinainen pelin apstraktio.
     */
    public Engine(String windowTitle, boolean vSync, IGameLogic gameLogic) {
        window = new Window(windowTitle, WIDTH, HEIGHT, vSync);
        this.gameLogic = gameLogic;
    }



    /**
     * Alustaa moottorin toiminnan.
     */
    public void init() throws Exception {
        window.init(true);
        Input.init(window.getWindowHandle());
        Time.init();
        gameLogic.init(window);
        inited = true;
    }

    /**
     * Käynnistää moottorin toiminnan.
     * Parametrina voidaan antaa sekunteja indikoiva numero
     * jos moottori halutaan sammuttaa tietyn ajan kuluttua.
     * @param seconds Kuinka kauvan moottori on käynnissä.
     *                Jos paramateri on nolla moottori ei sammu itsekseen.
     */
    public void run(int seconds) {
        try {
            init();
            loop(seconds);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            destroy();
        }
    }


    /**
     * Tämän metodin sisällä päivtetään muita moottorin osia.
     * Jos moottori halutaan sammuttaa tietyn ajan kuluttua voidaan antaa sekunteina toimiva parametri.
     * @param seconds Kuinka kauvan moottori on käynnissä. Jos nolla niin moottoria ei sammuteta tämän toimesta
     */
    private void loop(int seconds) {

        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;

        keepRunning = true;

        while (keepRunning) {
            elapsedTime = Time.getDeltaTime();
            accumulator += elapsedTime;

            if(Time.getElapsedTime() > seconds && seconds != 0) {
                keepRunning = false;
            }

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();

            if (!window.isvSync()) {
                sync();
            }
        }
    }


    /**
     * Moottorin update metodi jossa tarkistetaan suljetaanko ikkuna ja
     * päivitetään pelilogiikka.
     * @param interval aika päivitysten välillä
     */
    private void update(float interval) {

        gameLogic.update(interval);

        if (Input.isKeyDown(GLFW_KEY_ESCAPE) || window.close()) {
            keepRunning = false;
        }
    }


    /**
     * Metodi jolla odotetaan update loopin lopussa jotta saadaan
     * frama rate pidettyä oikeassa rytmissä
     */
    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = Time.getLastLoopTime() + loopSlot;
        while (Time.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
            }
        }
    }


    /**
     * Kutsuu ruudulle piirtäviä osia.
     */
    private void render() {
        gameLogic.render(window);
        window.update();
    }


    /**
     * Vapauttaa resurssit moottorin lopetettua toimintansa.
     */
    private void destroy() {
        Input.destroy();
        window.destroy();
        gameLogic.destroy();
        GLFW.glfwTerminate();
    }

    public IGameLogic getGameLogic() {
        return gameLogic;
    }

    public boolean isInited() {
        return inited;
    }

}
