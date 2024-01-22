public class Customer {
    private String name;
    private int pin;
    private Account savingsAccount;
    private Account checkingAccount;
    private TransactionHistory transactionHistory;

    public Customer(String name, int pin) {
        this.name = name;
        this.pin = pin;
        savingsAccount = new Account("Savings");
        checkingAccount = new Account("Checking");
        transactionHistory = new TransactionHistory();
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

    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
