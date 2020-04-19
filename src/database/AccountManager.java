package database;

import account.Account;
import account.User;

import java.sql.*;

public class AccountManager {
    private static Connection connection = ConnectionManager.getInstance().getConnection();


    public static boolean insert(User user, Account account) throws SQLException {
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

    public static void update(User user, Account account) {
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

    public static Account getRow(int accountId) throws SQLException {
        String sql = "SELECT * FROM Account " + "WHERE id = ?";
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
}
