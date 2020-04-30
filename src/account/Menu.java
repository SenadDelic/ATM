package account;

public class Menu {

    public static void welcome() {
        System.out.println("*************************************************");
        System.out.println("*********        WELCOME TO ATM         *********");
        System.out.println("*************************************************");
    }

    public static void menu() {
        System.out.println("Select below"
                + "\n\t 1 --> For creating account" +
                "\n\t 2 --> For Transfer " +
                "\n\t 3 --> For printing Users" +
                "\n\t 4 --> For transfer history" +
                "\n\t 5 --> For update account" +
                "\n\t 6 --> For delete account" +
                "\n\t 0 --> For Exit");
        System.out.print("\nEnter the transaction type: ");
    }

    public static void exit() {
        System.out.println("\n---------------------------------------------");
        System.out.println("********* Thank you. Have a nice day. **********");
        System.out.println("------------------------------------------------");
    }
}
