package main;

import game.DummyGame;
import engine.IGameLogic;
import engine.Engine;

public class Main {


    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new DummyGame();
            Engine engine = new Engine("GAME", vSync, gameLogic);
            engine.run();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}