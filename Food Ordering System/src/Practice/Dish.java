package Practice;

public class Dish {
    int id;
    String name;
    double price;

    public Dish(){}

    public Dish(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString(){
        return String.format("%-2d %-10s %6.2f", id, name, price);
    }
}
