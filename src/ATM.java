import java.util.Date;
import java.util.Scanner;

public class ATM {
    private static Scanner scanner = new Scanner(System.in);
    private static Customer customer;

    public static void start() {
        welcome();
    }

    private static void welcome() {
        Utils.clearScreen();
        System.out.println("Welcome to ATM\n");

        System.out.println("  (C)reate an account");
        System.out.println("  (L)eave");

        System.out.print("\n> ");
        String input = scanner.nextLine().toLowerCase().strip();

        switch (input) {
            case "c":
                newAccount();
                break;
            case "l":
                System.out.println("Have a nice day!");
                break;
            default:
                Utils.clearLine();
                System.out.println(Utils.color("Invalid input", "Red"));
                scanner.nextLine();
                welcome();
        }
    }

    private static void newAccount() {
        Utils.clearScreen();

        System.out.println("Account Creation\n");

        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Pin: ");
        int pin = scanner.nextInt();
        scanner.nextLine();

        customer = new Customer(name, pin);
        System.out.print(Utils.color("\nAccount Created!", "Green"));
        scanner.nextLine();
        login();
    }

    private static void login() {
        Utils.clearScreen();

        System.out.print("Enter your pin: ");
        int pin = scanner.nextInt();
        scanner.nextLine();

        if (pin != customer.getPin()) {
            System.out.print(Utils.color("Invalid Pin", "Red"));
            scanner.nextLine();
            login();
        }

        menu();
    }

    private static void menu() {
        printMenuScreen();
    }

    private static void printMenuScreen() {
        Date date = new Date();


        System.out.printf("""
                 ----------------------------------------------------------------------------
                |   █████╗ ████████╗███╗   ███╗                                              |
                |  ██╔══██╗╚══██╔══╝████╗ ████║                         %1$s
                |  ███████║   ██║   ██╔████╔██║                         %2$tm/%2$td/%2$ty | %2$tH:%2$tM%2$tp   |
                |  ██╔══██║   ██║   ██║╚██╔╝██║                                              |
                |  ██║  ██║   ██║   ██║ ╚═╝ ██║                                              |
                |  ╚═╝  ╚═╝   ╚═╝   ╚═╝     ╚═╝                                              |
                |----------------------------------------------------------------------------|
                |                                                                            |
                |  %3$s                                             |
                |                                                                            |
                |    ------------------------------        ------------------------------    |
                |   | 1                 WITHDRAWAL |      | CASH DEPOSIT               2 |   |
                |   | Withdraw money from your acc |      | Deposit cash into your acc   |   |
                |    ------------------------------        ------------------------------    |
                |                                                                            |
                |    -----------------------------         ------------------------------    |
                |   | 3            TRANSFER FUNDS |       | BALANCE ENQUIRY            4 |   |
                |   |Transfer fund to another acc |       | Check the balance of your acc|   |
                |    -----------------------------         ------------------------------    |
                |                                                                            |
                |    -----------------------------         ------------------------------    |
                |   | 5       TRANSACTION HISTORY |       | CHANGE PIN                 6 |   |
                |   |   Check transaction history |       | Change your account pin      |   |
                |    -----------------------------         ------------------------------    |
                |                                                                            |
                |  %4$s                                                           |
                |                                                                            |
                 ----------------------------------------------------------------------------
             """,

                Utils.bold("Welcome " + formatName(customer.getName())),
                date,
                Utils.bold("CHOOSE ONE OPTION TO CONTINUE"),
                Utils.color("LOGOUT AND EXIT", "Red", true)
                );
    }

    private static String formatName(String name) {
        if (name.length() > 11) {
            return name.substring(0, 8) + "...! |";
        } else {
            return name + "!" + " ".repeat(12 - name.length()) + "|";
        }
    }
}
