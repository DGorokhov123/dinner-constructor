package ru.practicum.dinner;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  This class gathers some IO logic into a single object
 */

public class InOutOps {

    private final Scanner scanner = new Scanner(System.in);

    int getInteger(int fromNumber) {
        int enteredNumber = 0;
        while (true) {
            boolean isCorrect = true;
            try {
                enteredNumber = scanner.nextInt();
            } catch (InputMismatchException e) {
                isCorrect = false;
            }
            scanner.nextLine();    // next line symbol
            if (enteredNumber < fromNumber) {
                isCorrect = false;
            }
            if (isCorrect) {
                return enteredNumber;
            }
            System.out.println("Повторите ввод. Ожидается целое число от " + fromNumber + " и больше.");
        }
    }


    int getInteger(int fromNumber, int toNumber) {
        int enteredNumber = 0;
        if (fromNumber > toNumber) {
            int tmp = fromNumber;
            fromNumber = toNumber;
            toNumber = tmp;
        }
        while (true) {
            boolean isCorrect = true;
            try {
                enteredNumber = scanner.nextInt();
            } catch (InputMismatchException e) {
                isCorrect = false;
            }
            scanner.nextLine();    // next line symbol
            if (enteredNumber < fromNumber || enteredNumber > toNumber) {
                isCorrect = false;
            }
            if (isCorrect) {
                return enteredNumber;
            }
            System.out.println("Повторите ввод. Ожидается целое число от " + fromNumber + " до " + toNumber + ".");
        }
    }


    String getNotEmptyString() {
        String entered = getString();
        while (entered.isEmpty()) {
            System.out.println("Повторите ввод. Ожидается непустая строка.");
            entered = getString();
        }
        return entered;
    }


    String getString() {
        String entered = scanner.nextLine().trim();
        entered = entered.replaceAll("\t+", " ");
        entered = entered.replaceAll("\s+", " ");
        return entered;
    }


    void println(String text) {
        System.out.println(text);
    }


}
