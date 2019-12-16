package main;

import game.CameraDemo;
import game.RollingCubeDemo;
import engine.IGameLogic;
import engine.Engine;

public class Main {


    /**
     * Main metodi joka aloittaa ohjelman suorittamisen.
     */
    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new CameraDemo();
            Engine engine = new Engine("GAME", vSync, gameLogic);
            engine.run(0);
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}