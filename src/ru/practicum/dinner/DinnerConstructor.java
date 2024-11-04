package ru.practicum.dinner;

import java.util.*;

public class DinnerConstructor {

    private final HashMap<String, ArrayList<String>> dishes = new HashMap<>();
    private final Random random = new Random();

    DinnerConstructor(boolean testMode) {
        if (testMode)  generateTestData();
    }

    void addDish(String dishType, String dishName) {
        ArrayList<String> dishNames = dishes.get(dishType);
        if (dishNames == null)  dishNames = new ArrayList<>();
        boolean isAlready = false;
        for (String dName : dishNames) {
            if (dName.equalsIgnoreCase(dishName))  isAlready = true;
        }
        if (!isAlready) {
            dishNames.add(dishName);
            dishes.put(dishType, dishNames);
        }
    }


    private ArrayList<String> getDishesByType(String type) {
        return dishes.get(type);
    }


    boolean checkType(String type) {
        return dishes.containsKey(type);
    }


    private ArrayList<String> getRandomCombo(ArrayList<String> types) {
        if (types == null || types.isEmpty())  return null;
        ArrayList<String> randomCombo = new ArrayList<>();
        for (String type : types) {
            ArrayList<String> typeDishes = getDishesByType(type);
            String currentDish = typeDishes.get(random.nextInt(typeDishes.size()));
            randomCombo.add(currentDish);
        }
        return randomCombo;
    }


    ArrayList<ArrayList<String>> makeCombos(ArrayList<String> types, int number) {
        if (types == null || types.isEmpty())  return null;
        ArrayList<ArrayList<String>> combos = new ArrayList<>();
        ArrayList<HashSet<String>> checkers = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < 1000; i++) {                               // To avoid unlimited iteration
            ArrayList<String> randomCombo = getRandomCombo(types);
            HashSet<String> checker = new HashSet<>();
            for (String s : randomCombo) {
                checker.add(s);
            }
            if (checker.size() < types.size())  continue;
            if (checkers.contains(checker))  continue;
            checkers.add(checker);
            combos.add(randomCombo);
            counter++;
            if (counter >= number)  break;
        }
        return combos;
    }


    private void generateTestData() {
        addDish("Первое", "Борщ");
        addDish("Первое", "Харчо");
        addDish("Первое", "Щи");
        addDish("Первое", "Уха");
        addDish("Второе", "Картошка с курицей");
        addDish("Второе", "Котлета с повидлом");
        addDish("Второе", "Семечки по татарски");
        addDish("Второе", "Каша гречневая");
        addDish("Десерт", "Торт Наполеон");
        addDish("Десерт", "Круассан");
        addDish("Десерт", "Мороженка");
        addDish("Десерт", "Медовик");
        addDish("Напиток", "Кружка пива");
        addDish("Напиток", "Томатный сок");
        addDish("Напиток", "Компот из яблок");
        addDish("Напиток", "Тыквенный латте");
        addDish("Напиток", "Чай Азерчай");
        addDish("Напиток", "Стакан воды");
    }


}
