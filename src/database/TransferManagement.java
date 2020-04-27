package database;

import account.Account;

import java.sql.*;
import java.util.Scanner;

public class TransferManagement {
    private final Account account = new Account();

    public void transferMoney(Scanner scanner, Connection connection) throws SQLException {
        AccountManager accountManager = new AccountManager();

        int sourceAccount = accountManager.isAccountValid(scanner, connection);
        int targetAccount = accountManager.isAccountValid(scanner, connection);

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        if (!isEnoughMoney(amount, sourceAccount, connection))
            System.out.println("There is no enough money on account");


    }
    
    public boolean isEnoughMoney(double amount, int sourceAccount, Connection connection) {
        ResultSet resultSet;
        String sql = "SELECT * FROM Account WHERE accountNumber = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, sourceAccount);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                account.setAccountId(resultSet.getInt("id"));
                account.setAccountNumber(resultSet.getInt("accountNumber"));
                account.setAmount(resultSet.getDouble("amount"));
            }
            System.out.println("amount " + account.getAmount());

            return (account.getAmount() > amount);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
