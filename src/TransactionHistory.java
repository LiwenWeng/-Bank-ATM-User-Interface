import java.util.ArrayList;

public class TransactionHistory {
    private ArrayList<String[]> accountTransactions;
    private ArrayList<String[]> securityTransactions;

    public TransactionHistory() {
        accountTransactions = new ArrayList<>();
        securityTransactions = new ArrayList<>();
    }

    public void updateAccountTransactions(String action, String isSuccess, String currentBalance) {
        accountTransactions.add(new String[]{formatId(accountTransactions.size()), action, isSuccess, currentBalance});
    }

    public void updateSecurityTransactions(String action, String isSuccess) {
        securityTransactions.add(new String[]{formatId(accountTransactions.size()), action, isSuccess});
    }

    public void printAccTransactionHistory() {
        System.out.println("Account Transaction History\n");
        for (String[] transaction : accountTransactions) {
            System.out.println(transaction[0] + ":");
            System.out.println("  " + transaction[1]);
            System.out.println("  " + transaction[2]);
            System.out.println("  " + transaction[3]);
        }
    }

    public void printSecTransactionHistory() {
        System.out.println("Security Transaction History\n");
        for (String[] transaction : securityTransactions) {
            System.out.println(transaction[0] + ":");
            System.out.println("  " + transaction[1]);
            System.out.println("  " + transaction[2]);
        }
    }

    private String formatId(int id) {
        id++;
        if (id == 1) return "0000";
        if (id < 10) return "000" + id;
        if (id < 100) return "00" + id;
        if (id < 1000) return "0" + id;
        return id + "";
    }
}
