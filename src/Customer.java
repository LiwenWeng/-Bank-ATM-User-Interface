public class Customer {
    private String name;
    private int pin;
    private Account savingsAccount;
    private Account checkingAccount;

    public Customer(String name, int pin) {
        this.name = name;
        this.pin = pin;
        this.savingsAccount = new Account();
        this.checkingAccount = new Account();
    }

    public String getName() {
        return name;
    }

    public int getPin() {
        return pin;
    }

    public Account getSavingsAccount() {
        return savingsAccount;
    }

    public Account getCheckingAccount() {
        return checkingAccount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
