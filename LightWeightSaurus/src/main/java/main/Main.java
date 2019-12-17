package main;

import game.BunnyModelDemo;
import game.CameraDemo;
import game.RollingCubeDemo;
import engine.IGameLogic;
import engine.Engine;

import java.util.Scanner;

public class Main {


    /**
     * Main metodi joka aloittaa ohjelman suorittamisen.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Engine starts");
        System.out.println("Witch demo u wanna see?");
        System.out.println("1. CameraDemo");
        System.out.println("2. RollingCubeDemo");
        System.out.println("3. Bunnyyyyy");
        System.out.println("Write a number: ");
        int number = Integer.valueOf(scanner.nextLine());

        if (number == 1) {
            try {
                boolean vSync = true;
                IGameLogic gameLogic = new CameraDemo();
                Engine engine = new Engine("GAME", vSync, gameLogic);
                engine.run(0);
            } catch (Exception excp) {
                excp.printStackTrace();
                System.exit(-1);
            }
        } else if (number == 2) {
            try {
                boolean vSync = true;
                IGameLogic gameLogic = new RollingCubeDemo();
                Engine engine = new Engine("GAME", vSync, gameLogic);
                engine.run(0);
            } catch (Exception excp) {
                excp.printStackTrace();
                System.exit(-1);
            }
        } else if (number == 3) {
            try {
                boolean vSync = true;
                IGameLogic gameLogic = new BunnyModelDemo();
                Engine engine = new Engine("GAME", vSync, gameLogic);
                engine.run(0);
            } catch (Exception excp) {
                excp.printStackTrace();
                System.exit(-1);
            }
        }
    }
}