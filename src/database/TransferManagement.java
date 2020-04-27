package database;

import account.Account;
import transfer.Transfer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
        else {
            insertTransfer(sourceAccount, targetAccount, amount, connection);
        }
    }

    public boolean insertTransfer(int sourceAccount, int targetAccount, double amount, Connection connection) throws SQLException {
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.INSERT_TRANSFER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, sourceAccount);
            preparedStatement.setInt(2, targetAccount);
            preparedStatement.setDouble(3, amount);

            int affected = preparedStatement.executeUpdate();
            if (affected == 1) {
                resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int newRs = resultSet.getInt(1);
                account.setAccountId(newRs);
            } else {
                System.err.println("No rows are affected :(");
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            if (resultSet != null) resultSet.close();
        }
        return true;
    }

    public List<Transfer> printTransfers(Connection connection) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(Constant.TRANSFER_QUERY_EVERYTHING_BASED_ON_ID)) {

            ArrayList<Transfer> transfers = new ArrayList<>();
            while (resultSet.next()){
                Transfer transfer = new Transfer();
                transfer.setTargetAccount(resultSet.getInt(2));
                transfer.setTargetAccount(resultSet.getInt(3));
                transfer.setAmountToTransfer(resultSet.getDouble(4));

                transfers.add(transfer);
            }
            return transfers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isEnoughMoney(double amount, int sourceAccount, Connection connection) {
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.ACCOUNT_QUERY_EVERYTHING_BASED_ON_ID)) {
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
