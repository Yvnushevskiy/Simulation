/*package org.yanushevskiy.Space;

import org.yanushevskiy.Actions.Simulationinfo;

import java.util.Scanner;
class SecondThread extends Thread {
    public static void waitForCancel() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите слово 'отмена' для изменения переменной:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("отмена")) {
                Main.game = false; // изменение значения переменной
                break;
            } else {
            }
        }

        scanner.close();
    }
    @Override
    public void run() {
        waitForCancel();
    }
}

*/