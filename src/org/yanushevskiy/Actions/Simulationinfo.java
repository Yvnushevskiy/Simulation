package org.yanushevskiy.Actions;


import org.yanushevskiy.Entities.Entity;

import java.util.HashMap;

public class Simulationinfo {
    private static boolean game = true;

    public static boolean isGame() {
        return game;
    }

    public static void setGame(boolean game) {
        Simulationinfo.game = game;
    }

    private static HashMap<String, Entity> Simulation = new HashMap<>();

    public static HashMap<String, Entity> getSimulation(){
        return Simulation;
    }
    private static int CurrentTurn = 0;

    public static int getCurrentTurn() {
        return CurrentTurn;
    }

    public static void setCurrentTurn(int currentTurn) {
        CurrentTurn = currentTurn;
    }
}
