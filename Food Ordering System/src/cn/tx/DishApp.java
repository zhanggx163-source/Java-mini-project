package cn.tx;

import java.util.*;

public class DishApp {

    static List<Dish> dishList = new ArrayList<>();
    static Map<Dish, Integer> personDish = new HashMap<>();

    static void main(String[] args) {
        //Display some dishes
        initDish();

        Scanner sc = new Scanner(System.in);

        outer:
        while(true){
            showMenu();
            System.out.println("Please input a number");
            int num = sc.nextInt();

            switch(num){
                case 0 -> {break outer;}
                case 1 ->{
                    while(true){
                        showDishMenu();
                        int id = sc.nextInt();
                        if(id == 0) break;//go back to last level
                        Dish orderedDish = dishList.get(id - 1);
                        System.out.println("Odered:" + orderedDish.name);
                        personDish.merge(orderedDish, 1, Integer:: sum);
                    }
                }
                case 2 -> {
                    showOrderedDish();
                    System.out.println("Please press 1 to pay, press 0 go back to last level");
                    int num1 = sc.nextInt();
                    if(num1 == 1){
                        pay();
                    }else if(num1 == 0){
                        break;
                    }
                }
                case 3 -> pay();
            }
        }
    }

    public static void showDishMenu(){
        System.out.println("Please Order");
        for (int i = 0; i < dishList.size(); i++) {
            Dish dish = dishList.get(i);
            System.out.printf("%-5d %-20s %8.2f%n",dish.id, dish.name, + dish.price);

        }
        System.out.println("Please input a number to order, press 0 go back");
    }

    public static void pay(){
        double totalPrice = 0.0;
        for(Dish dish: personDish.keySet()){
            totalPrice += dish.price * personDish.get(dish);
        }
        System.out.println("The total price is " + totalPrice);
    }

    public static void showOrderedDish(){
        for(Dish dish: personDish.keySet()){
            System.out.printf("%-30s %5d%n", dish.toString(), personDish.get(dish));
        }
    }

    public static void showMenu(){
        System.out.println("------Main Page-----");
        System.out.printf("%-15s %d%n", "Menu", 1);
        System.out.printf("%-15s %d%n", "Ordered Food", 2);
        System.out.printf("%-15s %d%n", "Pay", 3);
    }

    public static void initDish(){
        Dish dish1 = new Dish(1, "pizza", 15.0);
        Dish dish2 = new Dish(2, "hamburger", 12.0);
        Dish dish3 = new Dish(3, "orange juice", 5.0);
        dishList.add(dish1);
        dishList.add(dish2);
        dishList.add(dish3);
        dishList.add(new Dish(4, "hot dog", 10));
        dishList.add(new Dish(5, "coffee", 8));
        }
}
