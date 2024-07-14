package org.yanushevskiy.Actions;

import org.yanushevskiy.Entities.Entity;
import org.yanushevskiy.commons.Settings;

import java.util.*;  // Импортирует все классы из пакета java.util, такие как ArrayList, HashSet, LinkedList и другие.

public class BFS2D {  // Объявляет публичный класс org.yanushevskiy.Actions.BFS2D.
    static Settings settings = new Settings();

    public static List<int[]> bfs2D(int[] start, int[] goal) {  // Метод, выполняющий поиск в ширину в 2D матрице.
        int[][] matrix = new int[settings.getHeight()][settings.getWidth()];
        for (Entity entity : Simulationinfo.getSimulation().values()) {
            int x = entity.getX();
            int y = entity.getY();
            matrix[x][y] = 1;
        }
            matrix[start[0]][start[1]]=0;
            matrix[goal[0]][goal[1]]=0;
            int rows = matrix.length;
            int cols = matrix[0].length;
            int[][] directions = {
                    {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                    {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
            };

            Queue<int[]> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();
            Map<String, int[]> parentMap = new HashMap<>();

            queue.add(start);
            visited.add(Arrays.toString(start));
            parentMap.put(Arrays.toString(start), null);

            while (!queue.isEmpty()) {
                int[] current = queue.poll();

                if (Arrays.equals(current, goal)) {
                    break;
                }

                for (int[] direction : directions) {
                    int newRow = current[0] + direction[0];
                    int newCol = current[1] + direction[1];

                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && matrix[newRow][newCol] == 0) {
                        int[] neighbor = {newRow, newCol};

                        if (!visited.contains(Arrays.toString(neighbor))) {
                            visited.add(Arrays.toString(neighbor));
                            queue.add(neighbor);
                            parentMap.put(Arrays.toString(neighbor), current);
                        }
                    }
                }
            }

            List<int[]> path = new ArrayList<>();
            int[] step = goal;

            while (step != null) {
                path.add(step);
                step = parentMap.get(Arrays.toString(step));
            }

            Collections.reverse(path);
            path.remove(start);
            //path.remove(goal);
            return path;
        }
    }
