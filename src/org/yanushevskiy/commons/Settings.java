package org.yanushevskiy.commons;

import java.util.Random;

public class Settings {
        private static final int height = 10;
        private static final int width = 100;
        private static final double freeSpacePercent = 0.66;


        private final int rabbitsHP= getRandomNumber();
        private final int DamageWolfs = getRandomNumber();



        public static int getRandomNumber(){
                Random random = new Random();
                int maxqty = (int) (height*width*(1-freeSpacePercent)/7)+1;
                int qtyEntities = random.nextInt(1,maxqty);
                return qtyEntities;
        }

        public int getDamageWolfs() {
                return DamageWolfs;
        }

        public int getRabbitsHP() {
                return rabbitsHP;
        }

        public int getHeight(){
                return height;
        }
        public int getWidth(){
                return width;
        }
}



