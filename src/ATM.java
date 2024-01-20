import java.util.Date;
import java.util.Scanner;

public class ATM {
    private ATM() {}

    private static Scanner scanner = new Scanner(System.in);
    private static Customer customer;
    private static String[][] menuScreen;

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
            case "c" -> newAccount();
            case "l" -> System.out.println("Have a nice day!");
            default -> {
                Utils.clearLine();
                System.out.println(Utils.color("Invalid input", "Red"));
                scanner.nextLine();
                welcome();
            }
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

        System.out.println(Utils.bold("LOGIN"));

        System.out.print("\nEnter your pin: ");
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
        setMenuScreen();
        printMenuScreen();
        String input = scanner.nextLine();
        while (!input.equals("-1")) {
            setMenuScreen();
            menuScreen = Button.selectButton(input, menuScreen);
            printMenuScreen();
            input = scanner.nextLine();
        }
    }

    private static void setMenuScreen() {
        Utils.clearScreen();

        Date date = new Date();
        String menuScreenFormat = String.format(
                 """
                ------------------------------------------------------------------------------
                |   █████╗ ████████╗███╗   ███╗                         %1$s
                |  ██╔══██╗╚══██╔══╝████╗ ████║                         %2$tm/%2$td/%2$ty | %2$tH:%2$tM     |
                |  ███████║   ██║   ██╔████╔██║                                              |
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
                |  %4$s                                                                      |
                |                                                                            |
                ------------------------------------------------------------------------------
                 """,

                Utils.bold("Welcome " + formatName(customer.getName())) + "|",
                date,
                Utils.bold("CHOOSE ONE OPTION TO CONTINUE"),
                Utils.color("EXIT", "Red", true)
        );

        String[][] menuScreenArr = new String[30][79];
        int index = 0;
        for (int j = 0; j < menuScreenArr.length; j++) {
            for (int i = 0; i < menuScreenArr[j].length; i++) {
                menuScreenArr[j][i] = index > 2324 ? " " : menuScreenFormat.substring(index, index+1);
                index++;
            }
        }

        menuScreen = menuScreenArr;
    }

    private static void printMenuScreen() {
        for (int j = 0; j < menuScreen.length; j++) {
            System.out.print(j + ": ");
            for (int i = 0; i < menuScreen[j].length; i++) {
                System.out.print(menuScreen[j][i]);
            }
        }
        System.out.println();
    }

    private static String formatName(String name) {
        if (name.length() > 11) {
            return name.substring(0, 8) + "...! ";
        } else {
            return name + "!" + " ".repeat(12 - name.length());
        }
    }
}
