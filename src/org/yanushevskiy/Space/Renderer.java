package org.yanushevskiy.Space;

import org.yanushevskiy.Actions.Simulationinfo;
import org.yanushevskiy.Entities.Entity;
import org.yanushevskiy.Entities.Rabbit;
import org.yanushevskiy.Entities.Wolf;
import org.yanushevskiy.commons.Settings;

import java.util.Arrays;

public class Renderer {
    static Settings settings = new Settings();
    public static void showTheWorld(){
        String[][] world = new String[settings.getHeight()][settings.getWidth()];// создает видимый массив
        for (int i = 0; i < settings.getHeight() ; i++) {
            for (int j = 0; j < settings.getWidth(); j++) {
                world[i][j] = " ";                        // заполняет его значением
            }
        }
            for (Entity entity : Simulationinfo.getSimulation().values()) {
            int x = entity.getX();
            int y = entity.getY();
            world[x][y] = String.valueOf(entity);
        }
        for (int i = 0; i < settings.getHeight(); i++) {  // растягивает мир по y
            for (int j = 0; j < settings.getWidth() ; j++) {
                System.out.print(world[i][j]);
            }
            System.out.println();
        }
    }

}
