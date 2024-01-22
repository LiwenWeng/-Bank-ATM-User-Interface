public class Account {
    private double balance;
    private String name;

    public Account(String name) {
        balance = 0;
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void setBalance(double newBalance) {
        balance = newBalance;
    }
}
