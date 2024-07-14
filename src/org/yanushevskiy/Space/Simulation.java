package org.yanushevskiy.Space;


import org.yanushevskiy.Actions.Actions;
import org.yanushevskiy.Actions.Simulationinfo;

import java.sql.SQLOutput;

public class Simulation {
    public static void main(String[] args) throws InterruptedException {
        Actions.fillWorld();
        while (true) {
            if (Simulationinfo.isGame()) {
                Renderer.showTheWorld();
                System.out.println(Simulationinfo.getCurrentTurn());
                Thread.sleep(1000);
                Actions.makeMoveWolfs();
                Actions.makeMoveRabbits();
                Renderer.showTheWorld();
                System.out.println(Simulationinfo.getCurrentTurn());
            }
        }
    }
}
