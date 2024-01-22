import java.util.Scanner;

public class InputHandler {
    private InputHandler() {}

    public static void withdraw(Scanner scanner, Customer customer) {
        Utils.clearScreen();

        Account account = pickAccount(scanner, customer, "withdraw from");

        if (account.getBalance() == 0) {
            System.out.println(Utils.color("Your account balance is 0", "Red"));
            customer.getTransactionHistory().updateAccountTransactions(
                    "Withdrawing money from " + account.getName(),
                    Utils.color("Account balance was 0", "Red"),
                    account.getName() + " account balance: " + account.getBalance());
            scanner.nextLine();
            return;
        }

        System.out.println();
        System.out.print("Withdraw amount (needs to be multiple of 5): $");
        int amount = scanner.nextInt();
        scanner.nextLine();

        while (account.getBalance() < amount || amount % 5 != 0) {
            if (amount == 0) {
                customer.getTransactionHistory().updateAccountTransactions(
                        "Withdrew $" + amount + " from " + account.getName(),
                        Utils.color("Withdrew no money", "Blue"),
                        account.getName() + " account balance: " + account.getBalance());
                return;
            };

            Utils.clearLine();
            String reason = Utils.color(amount % 5 != 0 ? "Invalid amount" : "Insufficient funds!", "Red");
            System.out.print(reason);
            customer.getTransactionHistory().updateAccountTransactions(
                    "Withdrew $" + amount + " from " + account.getName(),
                    reason,
                    account.getName() + " account balance: " + account.getBalance());
            scanner.nextLine();
            Utils.clearLine();
            System.out.print("Withdraw amount (needs to be multiple of 5): $");
            amount = scanner.nextInt();
            scanner.nextLine();
        }

        if (amount == 0) {
            customer.getTransactionHistory().updateAccountTransactions(
                    "Withdrew $" + amount + " from " + account.getName(),
                    Utils.color("Withdrew no money", "Blue"),
                    account.getName() + " account balance: " + account.getBalance());
            return;
        }

        System.out.println();
        System.out.print("Enter the amount of 20 dollar bills you want to receive: ");
        int billsAmt = scanner.nextInt();
        scanner.nextLine();

        while (billsAmt * 20 > amount) {
            Utils.clearLine();
            System.out.print(Utils.color("Invalid amount", "Red"));
            customer.getTransactionHistory().updateAccountTransactions(
                    "Withdrew $" + amount + " from " + account.getName(),
                    Utils.color("Invalid amount of 20 dollar bills", "Red"),
                    account.getName() + " account balance: " + account.getBalance());
            scanner.nextLine();
            Utils.clearLine();
            System.out.print("Enter the amount of 20 dollar bills you want to receive: ");
            billsAmt = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.println();
        int leftoverAmt = amount - billsAmt * 20;
        int fiveDollarBillsAmt = leftoverAmt/5;
        System.out.println(Utils.color("You withdrew $" + amount + "!", "Green"));
        System.out.println("  " + billsAmt + " 20 dollar bills");
        System.out.println("  " + fiveDollarBillsAmt + " 5 dollar bills");
        account.setBalance(account.getBalance() - amount);
        customer.getTransactionHistory().updateAccountTransactions(
                "Withdrew $" + amount + " from " + account.getName(),
                Utils.color("Withdrawal was successful", "Green"),
                account.getName() + " account balance: " + account.getBalance());
        scanner.nextLine();
    }

    public static void deposit(Scanner scanner, Customer customer) {
        Utils.clearScreen();

        Account account = pickAccount(scanner, customer, "deposit to");

        System.out.print("\nEnter the amount to deposit: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print(Utils.color("\nDeposited $" + amount + " to account!", "Green"));
        account.setBalance(account.getBalance() + amount);
        customer.getTransactionHistory().updateAccountTransactions(
                "Deposited $" + amount + " to " + account.getName(),
                Utils.color("Deposit was successful", "Green"),
                account.getName() + " account balance: " + account.getBalance());
        scanner.nextLine();
    }

    public static void transfer(Scanner scanner, Customer customer) {
        Utils.clearScreen();

        Account fromAcc = pickAccount(scanner, customer, "transfer from");
        Account toAcc = fromAcc.getName().equals("Savings") ? customer.getCheckingAccount() : customer.getSavingsAccount();

        if (fromAcc.getBalance() == 0) {
            System.out.println(Utils.color("\nYour account balance is 0", "Red"));
            customer.getTransactionHistory().updateAccountTransactions(
                    "Transferring money from " + fromAcc.getName() + " to " + toAcc.getName(),
                    Utils.color(fromAcc.getName() + " account balance was 0", "Red"),
                    fromAcc.getName() + " account balance: " + fromAcc.getBalance() + "\n" + toAcc.getName() + " account balance: " + toAcc.getBalance());
            scanner.nextLine();
            return;
        }

        System.out.print("\nEnter the amount to transfer: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        while (amount > fromAcc.getBalance()) {
            Utils.clearLine();
            System.out.print(Utils.color("Insufficient amount", "Red"));
            customer.getTransactionHistory().updateAccountTransactions(
                    "Transferring " + amount + " from " + fromAcc.getName() + " to " + toAcc.getName(),
                    Utils.color(fromAcc.getName() + " account balance was not enough", "Red"),
                    fromAcc.getName() + " account balance: " + fromAcc.getBalance() + "\n" + toAcc.getName() + " account balance: " + toAcc.getBalance());
            scanner.nextLine();
            Utils.clearLine();
            System.out.print("Enter the amount to transfer: $");
            amount = scanner.nextDouble();
            scanner.nextLine();
        }

        fromAcc.setBalance(fromAcc.getBalance() - amount);
        toAcc.setBalance(toAcc.getBalance() + amount);
        System.out.println(Utils.color("\nTransfered " + amount + " to " + toAcc.getName() + " account", "Green"));
        System.out.print(toAcc.getName() + " account now has $" + toAcc.getBalance());
        customer.getTransactionHistory().updateAccountTransactions(
                "Transferring " + amount + " from " + fromAcc.getName() + " to " + toAcc.getName(),
                Utils.color("Transfer was successful", "Green"),
                fromAcc.getName() + " account balance: " + fromAcc.getBalance() + "\n" + toAcc.getName() + " account balance: " + toAcc.getBalance());
        scanner.nextLine();
    }

    public static void accountInfo(Scanner scanner, Customer customer) {
        Utils.clearScreen();

        System.out.println("Savings account: $" + customer.getSavingsAccount().getBalance());
        System.out.println("Checking account: $" + customer.getCheckingAccount().getBalance());
        customer.getTransactionHistory().updateSecurityTransactions("Checked account balance", Utils.color("Successfully checked", "Green"));

        scanner.nextLine();
    }

    public static void transactionHistory(Scanner scanner, Customer customer) {
        Utils.clearScreen();

        System.out.println("Transaction History: \n");
        System.out.println("  (A)ccount Transactions");
        System.out.println("  (S)ecurity Transactions");
        System.out.print("\n>");

        String choice = scanner.nextLine().toLowerCase();
        while (!choice.equals("a") && !choice.equals("s")) {
            Utils.clearLine();
            System.out.print(Utils.color("Invalid Input", "Red"));
            scanner.nextLine();
            Utils.clearLine();
            System.out.print(">");
            choice = scanner.nextLine().toLowerCase();
        }

        Utils.clearScreen();

        if (choice.equals("a")) {
            customer.getTransactionHistory().updateSecurityTransactions("Checked Account Transaction History", Utils.color("Successfully checked", "Green"));
            customer.getTransactionHistory().printAccTransactionHistory();
        } else {
            customer.getTransactionHistory().updateSecurityTransactions("Checked Security Transaction History", Utils.color("Successfully checked", "Green"));
            customer.getTransactionHistory().printSecTransactionHistory();
        }

        scanner.nextLine();
    }

    public static void updatePin(Scanner scanner, Customer customer) {
        Utils.clearScreen();

        System.out.print("Enter a new PIN: ");
        int newPin = scanner.nextInt();
        scanner.nextLine();
        System.out.print(Utils.color("Changed PIN to " + newPin, "Green"));
        customer.setPin(newPin);
        customer.getTransactionHistory().updateSecurityTransactions("Changed PIN", Utils.color("Successfully changed PIN to " + newPin, "Green"));
        scanner.nextLine();
    }

    private static Account pickAccount(Scanner scanner, Customer customer, String function) {
        System.out.println("Pick an account to " + function + ":\n");
        System.out.println("  (S)avings account");
        System.out.println("  (C)heckings account");
        System.out.print("\n>");
        String acc = scanner.nextLine().toLowerCase();

        while (!acc.equals("s") && !acc.equals("c")) {
            Utils.clearLine();
            System.out.print(Utils.color("Invalid Input!", "Red"));
            scanner.nextLine();
            Utils.clearLine();
            System.out.print(">");
            acc = scanner.nextLine().toLowerCase();
        }

        return acc.equals("s") ? customer.getSavingsAccount() : customer.getCheckingAccount();
    }
}
