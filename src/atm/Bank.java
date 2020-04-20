package atm;

import account.Account;
import account.Menu;
import account.User;
import database.AccountManager;
import database.ConnectionManager;
import database.TransferManagement;

import java.sql.SQLException;
import java.util.Scanner;

public class Bank {
    private Account account = new Account();
    private AccountManager accountManager = new AccountManager();
    private TransferManagement transferManagement = new TransferManagement();

    public void bank() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            Menu.menu();

            switch (choice = scanner.nextByte()) {
                case 1:
                    printUsers();
                    break;
                case 2:
                    createAccount(scanner);
                    break;
            }
        } while (choice != 0);

        scanner.close();
        ConnectionManager.getInstance().closeConnection();
    }

    private void printUsers() {
        AccountManager.displayAllRows();
    }

    private void createAccount(Scanner scanner) throws SQLException {
        String firstName, lastName;
        int accountNumber;
        double amount;

        System.out.print("Enter your first name: ");
        firstName = scanner.next();
        System.out.print("Enter your last name: ");
        lastName = scanner.next();

        System.out.print("Choose your account number: ");
        accountNumber = scanner.nextInt();
        while (account.isaAccountNumberAlreadyExist(accountNumber) || account.isAccountNumberNegative(accountNumber)) {
            System.out.print("Account already exist or you entered negative account number. \nTry again: ");
            accountNumber = scanner.nextInt();
        }

        System.out.print("Enter amount: ");
        amount = scanner.nextDouble();
        while (account.isNegativeAmount(amount)) {
            System.out.print("You can't enter negative amount! \\nTry again:");
            amount = scanner.nextDouble();
        }

        User user = new User(firstName, lastName);
        account = new Account(user, accountNumber, amount);

        AccountManager.insert(user, account);
    }

    public int checkSourceAccount(Scanner scanner) throws SQLException {
        System.out.print("Enter source account: ");
        int sourceAccount = scanner.nextInt();

        while (account.isaAccountNumberAlreadyExist(sourceAccount)){
            System.out.println("Invalid source account! " + "\nTry again: ");
            sourceAccount = scanner.nextInt();
        }
         return sourceAccount;
    }

    public int checkTargetAccount(Scanner scanner) throws SQLException {
        System.out.print("Enter target account: ");
        int targetAccount = scanner.nextInt();

        while (account.isaAccountNumberAlreadyExist(targetAccount)){
            System.out.println("Invalid target account! " + "\nTry again: ");
            targetAccount = scanner.nextInt();
        }
        return targetAccount;
    }

    public void transferMoney(){

    }
}
