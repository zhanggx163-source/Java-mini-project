package cn.tx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainMenu {
    static List<Bill> billList = new ArrayList<>();

    static{
        billList.add(new Bill("Food", 102.50, "Credit Card", "Expense", "01/01/2026", "Friends"));
        billList.add(new Bill("Transport", 18.50, "Debit Card", "Expense", "03/01/2026", "Bus"));
        billList.add(new Bill("Shopping", 235.80, "Credit Card", "Expense", "05/02/2026", "Clothes"));
        billList.add(new Bill("Coffee", 6.20, "Cash", "Expense", "06/02/2026", "Starbucks"));
        billList.add(new Bill("Rent", 1200.00, "Bank Transfer", "Expense", "10/02/2026", "January Rent"));
        billList.add(new Bill("Utilities", 89.75, "Debit Card", "Expense", "12/03/2026", "Electricity"));
        billList.add(new Bill("Entertainment", 45.00, "Credit Card", "Expense", "15/03/2026", "Movie"));
        billList.add(new Bill("Groceries", 156.30, "Debit Card", "Expense", "18/03/2026", "Supermarket"));
        billList.add(new Bill("Salary", 5000.00, "Bank Transfer", "Income", "25/04/2026", "January Salary"));
        billList.add(new Bill("Freelance", 850.00, "Bank Transfer", "Income", "27/04/2026", "Website Project"));
        billList.add(new Bill("Gift", 200.00, "Cash", "Income", "30/06/2026", "Birthday Gift"));
    }

    static void main() {
        run();

    }

    public static void showMain(){
        System.out.println("---Quick Expense Tracker---");
        System.out.println("1.Add Expense 2.Delete Expense 3.Search Expense 4.Exit");
        System.out.println("Please input a number[1-4]");
    }

    public static void run(){

            boolean flag = true;
            Scanner sc = new Scanner(System.in);
            while(flag){
                showMain();
                int option = sc.nextInt();
                switch(option){
                    case 1 -> addBill();
                    case 2 -> delBill();
                    case 3 -> select();
                    case 4 -> flag = false;
                }
            }
        System.out.println("See you!");
    }

    public static void delBill(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input id");
        int id = sc.nextInt();
        billList.remove(id - 1);
        System.out.println("Deleted!");
    }

    public static void addBill(){
        Scanner sc = new Scanner(System.in);
        Bill bill = new Bill();
        System.out.println("Please input name");
        //"Food", 102.50, "Credit Card", "Expense", "01/01/2026", "Friends"
        bill.setName(sc.next());

        System.out.println("Please input amount");
        bill.setAmount(sc.nextDouble());

        System.out.println("Please input type");
        bill.setType(sc.next());

        System.out.println("Please input account");
        bill.setAccount(sc.next());

        System.out.println("Please input date");
        bill.setTime(sc.next());

        System.out.println("Please input description");
        bill.setDesc(sc.next());

        billList.add(bill);
        System.out.println("Added!");
    }
    //three ways: 
    public static void select(){
        System.out.println("View Expenses");
        System.out.println("1.Search all 2.Based on time 3.Based on type");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch(option){
            case 1 -> selectAll();
            case 2 -> selectByDate();
            case 3 -> selectByType();
        }
        showMain();
    }

    public static void selectAll(){
        print(billList);
    }

    public static void print(List<Bill> billList){
        System.out.printf("%-4s %-15s %-10s %-15s %-8s %-13s %-20s%n", "id", "name", "type", "account", "amount", "time", "desc");
        for (int i = 0; i < billList.size(); i++) {
            Bill bill = billList.get(i);
            System.out.printf("%-4d %-15s %-10s %-15s %-8.2f %-13s %-20s%n",
                    i + 1,
                    bill.getName(),
                    bill.getType(),
                    bill.getAccount(),
                    bill.getAmount(),
                    bill.getTime(),
                    bill.getDesc());
        }
                

    }

    public static void selectByDate(){
        //创建一个时间格式化的对象
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("View Expenses");
        Scanner sc = new Scanner(System.in);

        System.out.println("Please input start date");
        String start = sc.next();

        System.out.println("Please input end date");
        String end = sc.next();

        Stream<Bill> stream = billList.stream();
        List<Bill> filteredList = stream.filter(bill -> {
            String time = bill.getTime();
            try {
                Date startDate = format.parse(start);
                Date endDate = format.parse(end);
                Date timeDate = format.parse(time);
                if(timeDate.before(endDate) && timeDate.after(startDate)) return true;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return false;
        }).collect(Collectors.toList());
        print(filteredList);
    }

    public static void selectByType(){
        System.out.println("View Expenses");
        System.out.println("Please input \"Expense\" or \"Income");
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        Stream<Bill> stream = billList.stream();
        List<Bill> filteredList = stream.filter(bill -> {
            String type1 = bill.getType();
            return type1.equals(type);
        }).collect(Collectors.toList());
        print(filteredList);
    }
}
