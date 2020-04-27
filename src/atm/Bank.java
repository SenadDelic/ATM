package atm;

import account.Account;
import account.Menu;
import account.User;
import database.AccountManager;
import database.ConnectionManager;
import database.TransferManagement;
import transfer.Transfer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private final AccountManager accountManager = new AccountManager();
    private final TransferManagement transferManagement = new TransferManagement();
    private static final Connection connection = ConnectionManager.getInstance().getConnection();
    private final Scanner scanner = new Scanner(System.in);

    public void bank() throws SQLException {
        int choice;
        do {
            Menu.menu();

            switch (choice = scanner.nextByte()) {
                case 1:
                    accountManager.createAccount(scanner, connection, accountManager);
                    break;
                case 2:
                    transferManagement.transferMoney(scanner, connection);
                    break;
                case 3:
                    accountManager.displayAllRows(connection);
                    break;
                case 4:
                    List<Transfer> transferList = transferManagement.printTransfers(connection);
                    transferList.stream().map(transfer -> "id " + transfer.getId() +
                            "Source account id: " + transfer.getSourceTarget() +
                            "Target account id " + transfer.getTargetAccount() + " amount = " +
                            transfer.getAmountToTransfer()).forEach(System.out::println);
                    break;

            }
        } while (choice != 0);

        scanner.close();
        ConnectionManager.getInstance().closeConnection();
    }
}
