package Practice;

import java.util.*;

public class DishApp {
    static List<Dish> dishes = new ArrayList<>();
    static Map<Dish, Integer> orderedList = new HashMap<>();

    static void main(String[] args) {
        initMenu();
        while (true) {
            mainPage();

            Scanner sc = new Scanner(System.in);

            int num = sc.nextInt();
            switch (num) {
                case 1 -> {
                    while (true) {
                        menu();
                        System.out.println("Please press number to order, press 0 to go back");
                        int id = sc.nextInt();
                        if (id == 0) break;
                        //add ordered food into orderedList
                        if (id > 0 && id <= dishes.size()) {
                            orderedList.merge(dishes.get(id - 1), 1, Integer::sum);
                        }
                    }
                }

                case 2 -> orderedFood();
                case 3 -> pay();


            }
        }

    }

    public static void mainPage() {
        System.out.println("----Main Page----");
        System.out.println("1. Menu");
        System.out.println("2. Ordered Food");
        System.out.println("3. Pay");
        System.out.println("Please input a number to choose your service");
    }

    public static void initMenu() {
        Dish dish1 = new Dish(1, "pizza", 12.0);
        Dish dish2 = new Dish(2, "hamburger", 15.0);
        dishes.add(dish1);
        dishes.add(dish2);
        dishes.add(new Dish(3, "lemonade", 4.9));
    }

    public static void menu() {
        //display all the dishes
        for (int i = 0; i < dishes.size(); i++) {
            System.out.println(dishes.get(i));
        }
    }

    public static void orderedFood() {
        if (orderedList.isEmpty()) System.out.println("No order");
        for (Dish dish : orderedList.keySet()) {
            System.out.printf("%-20s %5d%n", dish.toString(), orderedList.get(dish));
        }
    }

    public static void pay() {
        double totalPrice = 0.0;
        for (Dish dish : orderedList.keySet()) {
            totalPrice += orderedList.get(dish) * dish.price;
        }
        System.out.println("Total price is " + totalPrice);
    }

}


