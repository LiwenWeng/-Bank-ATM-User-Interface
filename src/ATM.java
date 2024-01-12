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
        menu();
    }

    private static void menu() {
        Utils.clearScreen();

        System.out.print("Enter your pin: ");
        int pin = scanner.nextInt();
        scanner.nextLine();

        if (pin != customer.getPin()) {
            System.out.print(Utils.color("Invalid Pin", "Red"));
            scanner.nextLine();
            menu();
        }
    }
}
