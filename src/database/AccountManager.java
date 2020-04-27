package database;

import account.Account;
import account.User;

import java.sql.*;
import java.util.Scanner;

public class AccountManager {
    private Account account = new Account();

    public boolean insert(User user, Account account, Connection connection) throws SQLException {
        String sql = "INSERT INTO Account (firstName, lastName, accountNumber, amount) "
                + "VALUES(?, ?, ?, ?)";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, account.getAccountNumber());
            preparedStatement.setDouble(4, account.getAmount());

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
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (resultSet != null) resultSet.close();
        }
        return true;
    }

    public void update(User user, Account account, Connection connection) {
        String sql = "UPDATE Account SET " +
                "firstName = ?, lastName = ?, accountNumber = ?, amount = ? " +
                "WHERE Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, account.getAccountNumber());
            preparedStatement.setDouble(4, account.getAmount());
            preparedStatement.setInt(5, account.getAccountId());

            if (preparedStatement.executeUpdate() == 1)
                System.out.println("Update was successful");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Update failed!");
        }
    }

    public boolean delete(int accountId, Connection connection) {
        String sql = "DELETE FROM Account WHERE id = ?";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, accountId);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void displayAllRows(Connection connection) {
        String sql = "SELECT * FROM Account";

        try (
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql)
        ) {
            System.out.printf("%-3s  %-12s  %-11s  %-16s  %-11s", rs.getMetaData().getColumnName(1), rs.getMetaData().getColumnName(2)
                    , rs.getMetaData().getColumnName(3), rs.getMetaData().getColumnName(4), rs.getMetaData().getColumnName(5));

            while (rs.next()) {
                System.out.printf("\n%-3d  %-12s  %-11s  %-16d  %-11.2f", rs.getInt(1), rs.getString(2), rs.getString(3)
                        , rs.getInt(4), rs.getDouble(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getRow(int accountId, Connection connection) throws SQLException {
        String sql = "SELECT * FROM Account " + "WHERE accountNumber = ?";
        ResultSet rs = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getObject("id", Integer.class));
                account.setAccountNumber(rs.getObject("accountNumber", Integer.class));
                account.setAmount(rs.getObject("amount", Double.class));

                return account;
            } else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (rs != null) rs.close();
        }
    }

    public int isAccountValid(Scanner scanner, Connection connection) throws SQLException {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Account WHERE accountNumber = ?";
        System.out.print("Enter account number: ");
        int accountNUmber = scanner.nextInt();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountNUmber);
            resultSet = preparedStatement.executeQuery();

          if (resultSet.next()) {
              Account account = new Account();
              account.setAccountNumber(resultSet.getInt("accountNumber"));
              System.out.println("accountId " +   account.getAccountNumber());

              return account.getAccountNumber();
          }
            else
                System.out.println("Invalid source accountNUmber!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
        }
        return accountNUmber;
    }

    public void createAccount(Scanner scanner, Connection connection, AccountManager accountManager) throws SQLException {
        String firstName, lastName;
        int accountNumber;
        double amount;

        System.out.print("Enter your first name: ");
        firstName = scanner.next();
        System.out.print("Enter your last name: ");
        lastName = scanner.next();

        System.out.print("Choose your account number: ");
        accountNumber = scanner.nextInt();
        while (account.isaAccountNumberAlreadyExist(accountNumber, connection) || account.isAccountNumberNegative(accountNumber)) {
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

        accountManager.insert(user, account, connection);
    }
}
