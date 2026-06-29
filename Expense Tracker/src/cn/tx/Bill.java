package cn.tx;

public class Bill {
    private String name;
    private String account;
    private String type;
    private double amount;
    private String time;
    private String desc;

    public Bill(){}
    public Bill(String name, double amount, String account, String type, String time, String desc) {
        this.name = name;
        this.account = account;
        this.type = type;
        this.amount = amount;
        this.time = time;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double total) {
        this.amount = total;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
