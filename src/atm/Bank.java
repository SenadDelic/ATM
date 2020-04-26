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
    private AccountManager accountManager = new AccountManager();
    private TransferManagement transferManagement = new TransferManagement();

    public void bank() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            Menu.menu();

            switch (choice = scanner.nextByte()) {
                case 1:
                    AccountManager.displayAllRows();
                    break;
                case 2:
                    accountManager.createAccount(scanner);
                    break;
            }
        } while (choice != 0);

        scanner.close();
        ConnectionManager.getInstance().closeConnection();
    }
}
