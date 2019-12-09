package engine;

import engine.Window;

public interface IGameLogic {

    /**
     * Alustaa pelilogiikan.
     * @param window on moottorin ikkuna johon pelin halutaan olevan yhteydessä
     * @throws Exception
     */
    void init(Window window) throws Exception;

    /**
     * Päivittää pelilogiikan.
     * @param interval päivitysten välinen aika.
     */
    void update(float interval);

    /**
     * Piirtää grafiikan pelilogiikan osalta.
     * @param window ikkuna johon hlutaan piirtää.
     */
    void render(Window window);

    /**
     * Vapauttaa resurssit moottorin lopetettua toimintansa.
     */
    void destroy();
}
