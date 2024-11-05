package ru.practicum.dinner;

import java.util.ArrayList;

public class Main {

    static final boolean TEST_MODE = false;    // It generates sample data to avoid extra manual work
    static final DinnerConstructor dc = new DinnerConstructor(TEST_MODE);
    static final InOutOps IOMachine = new InOutOps();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int command = IOMachine.getInteger(1, 3);
            switch (command) {
                case 1:
                    addNewDish();
                    break;
                case 2:
                    generateDishCombo();
                    break;
                case 3:
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = IOMachine.getNotEmptyString();
        System.out.println("Введите название блюда:");
        String dishName = IOMachine.getNotEmptyString();
        dc.addDish(dishType, dishName);
    }


    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");
        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = IOMachine.getInteger(1);
        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        ArrayList<String> typeItems = new ArrayList<>();
        String nextItem = IOMachine.getString();
        while (!nextItem.isEmpty()) {
            if (dc.checkType(nextItem)) {
                typeItems.add(nextItem);
            } else {
                System.out.println("Тип обеда " + nextItem + " отсутствует в меню. Введите другой тип.");
            }
            nextItem = IOMachine.getString();
        }
        if (typeItems.isEmpty()) {
            System.out.println("Вы ввели пустой список типов обеда. Получайте пустой список комбо!");
            return;
        }
        ArrayList<ArrayList<String>> combos = dc.makeCombos(typeItems, numberOfCombos);
        for (int i = 0; i < combos.size(); i++) {
            System.out.println("Комбо " + (i + 1));
            ArrayList<String> comboDish = combos.get(i);
            boolean isFirstDish = true;
            for (String dish : comboDish) {
                if (isFirstDish) {
                    System.out.print("[");
                } else {
                    System.out.print(", ");
                }
                System.out.print(dish);
                isFirstDish = false;
            }
            System.out.println("]");
        }

    }
}
