package database;

import account.Account;

import java.sql.SQLException;
import java.util.Scanner;

public class TransferManagement {

    private Account account = new Account();

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
