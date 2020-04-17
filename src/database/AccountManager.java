package database;

import account.Account;
import account.User;

import java.sql.*;

public class AccountManager {
    private static Connection connection = ConnectionManager.getInstance().getConnection();


    public static boolean insert(Account account, User user) throws SQLException {
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

}
