package org.yanushevskiy.Actions;
import java.util.*;

import org.yanushevskiy.Entities.*;
import org.yanushevskiy.commons.Settings;


public class Actions {
    static Settings settings = new Settings();

    public static int[] getFreeCoordinate() {
        Random random = new Random();
        int[][] array = new int[settings.getHeight()][settings.getWidth()];  // создали массив
        int randomIndex = 0;
        List<int[]> free = new ArrayList<>();
        for (String key : Simulationinfo.getSimulation().keySet()) { // сменили занятые на 1
            Entity entity = Simulationinfo.getSimulation().get(key);
            int x = entity.getX();
            int y = entity.getY();
            if (x < array.length && y < array[x].length) {
                array[x][y] = 1;
            }
        }
        for (int i = 0; i < array.length; i++) {  // нашли кординаты всех нулей.
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    free.add(new int[]{i, j});
                }
            }
        }
        randomIndex = random.nextInt(free.size()); //взяли случайную кординату.

        return free.get(randomIndex);
    }

    public static void fillWorld() {
        addEntity(Wolf.class);
        addEntity(Rabbit.class);
        addEntity(Grass.class);
        addEntity(Tree.class);
        addEntity(Rock.class);

    }

    static void addEntityByCoordinate(Entity entity,int[] coordinates){
        Simulationinfo.getSimulation().put(UUID.randomUUID().toString(), entity);
        entity.setX(coordinates[0]);
        entity.setY(coordinates[1]);
    }
    static void addEntity(Class<? extends Entity> entityClass) {
        for (int i = 0; i < Settings.getRandomNumber(); i++) {// !!!! может работать непредсказуемо.
            try {
                int[] x = getFreeCoordinate();
                Entity entity = entityClass.getDeclaredConstructor().newInstance();
                entity.setX(x[0]);
                entity.setY(x[1]);
                Simulationinfo.getSimulation().put(" " + i + entityClass, entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

   public static void makeMoveWolfs() {
        List<String> wolfs = getWolfsKeys(Simulationinfo.getSimulation());
            for(String wolf: wolfs) {
                Entity object = Simulationinfo.getSimulation().get(wolf);
                int x = object.getX();
                int y = object.getY();
                int[] coordinate = {x, y};
                int[] target = findClosestCoordinate(coordinate,getRabbitCoordinates(Simulationinfo.getSimulation()));
                List<int[]> wayToTarget = BFS2D.bfs2D(coordinate, target);
                if (wayToTarget.size() == 1) {
                    deleteEntityByCoordinates(target);
                    addEntityByCoordinate(new Rabbit(),getFreeCoordinate());
                }else if(wayToTarget.size()>1){
                    deleteEntityByCoordinates(coordinate);
                    addEntityByCoordinate(new Wolf(), wayToTarget.get(0));
            }
        }
   }
    public static void makeMoveRabbits() {
        List<String> rabbits = getRabbitsKeys((Simulationinfo.getSimulation()));
        for(String rabbit: rabbits) {
            Entity object = Simulationinfo.getSimulation().get(rabbit);
            int x = object.getX();
            int y = object.getY();
            int[] coordinate = {x, y};
            int[] target = findClosestCoordinate(coordinate,getGrassesCoordinates(Simulationinfo.getSimulation()));
            List<int[]> wayToTarget = BFS2D.bfs2D(coordinate, target);
            if (wayToTarget.size() == 1) {
                deleteEntityByCoordinates(target);
                addEntityByCoordinate(new Grass(),getFreeCoordinate());
            }else if(wayToTarget.size()>1){
                deleteEntityByCoordinates(coordinate);
                addEntityByCoordinate(new Rabbit(), wayToTarget.get(0));
            }
        }Simulationinfo.setCurrentTurn(Simulationinfo.getCurrentTurn()+1);
    }
    public static List<String> getWolfsKeys(HashMap<String, Entity> simulation) {
        List<String> WolfsKeys = new ArrayList<>();
        for (Map.Entry<String, Entity> entry : simulation.entrySet()) {
            if (entry.getValue() instanceof Wolf) {
                WolfsKeys.add(entry.getKey());
            }
        }
        return WolfsKeys;
    }
    public static List<String> getRabbitsKeys(HashMap<String, Entity> simulation) {
        List<String> rabbitKeys = new ArrayList<>();
        for (Map.Entry<String, Entity> entry : simulation.entrySet()) {
            if (entry.getValue() instanceof Rabbit) {
                rabbitKeys.add(entry.getKey());
            }
        }
        return rabbitKeys;
    }


    public static double calculateDistance(int[] point1, int[] point2) {
        return Math.sqrt(Math.pow(point2[0] - point1[0], 2) + Math.pow(point2[1] - point1[1], 2));
    }

    public static int[] findClosestCoordinate(int[] target, List<int[]> coordinates) {
        int[] closestCoordinate = coordinates.get(0);
        double closestDistance = calculateDistance(target, closestCoordinate);

        for (int i = 1; i < coordinates.size(); i++) {
            int[] currentCoordinate = coordinates.get(i);
            double distance = calculateDistance(target, currentCoordinate);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestCoordinate = currentCoordinate;
            }
        }
        return closestCoordinate;
    }

    public static List<int[]> getRabbitCoordinates(HashMap<String, Entity> simulation) {
        List<int[]> rabbitCoordinates = new ArrayList<>();
        for (Map.Entry<String, Entity> entry : simulation.entrySet()) {
            if (entry.getValue() instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) entry.getValue();
                rabbitCoordinates.add(new int[]{rabbit.getX(), rabbit.getY()});
            }
        }
        return rabbitCoordinates;
    }

    public static List<int[]> getGrassesCoordinates(HashMap<String, Entity> simulation) {
        List<int[]> grassCoordinates = new ArrayList<>();
        for (Map.Entry<String, Entity> entry : simulation.entrySet()) {
            if (entry.getValue() instanceof Grass) {
                Grass grass = (Grass) entry.getValue();
                grassCoordinates.add(new int[]{grass.getX(), grass.getY()});
            }
        }
        return grassCoordinates;
    }

    public static void deleteEntityByCoordinates(int[] coordinate) {
        HashMap<String, Entity> simulationCopy = new HashMap<>(Simulationinfo.getSimulation());
            for(var lox: simulationCopy.entrySet()){
                if (lox.getValue().getX() == coordinate[0] && lox.getValue().getY()==coordinate[1]) {
                    Simulationinfo.getSimulation().remove(lox.getKey());
                }
            }
        }
}