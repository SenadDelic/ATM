package database;

import account.Account;
import account.User;

import java.sql.*;
import java.util.Scanner;

public class AccountManager {
    private Account account = new Account();

    public void insertAccount(User user, Account account, Connection connection) throws SQLException {
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS)) {
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
            }
        } catch (SQLException e) {
            System.out.println("Something is wrong");
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
        }
    }

    public void update(Scanner scanner, Connection connection) throws SQLException {
        int accountId = isAccountValid(scanner, connection);

        System.out.println("Select: " +
                "\n\t\"A\" --> if you want to update your first name" +
                "\n\t\"B\" --> if you want to update last name" +
                "\n\t\"C\" --> if you want to update first and last name" +
                "\n\t\"D\" --> if you want to change your account number" +
                "\n\t\"E\" --> if you want to exit");

        char choice = scanner.next().toUpperCase().charAt(0);

        switch (choice) {
            case 'A':
                updateUserFirstName(accountId, scanner, connection);
                break;
            case 'B':
                updateUserLastName(accountId, scanner, connection);
                break;
            case 'C':
                updateUserFirstName(accountId, scanner, connection);
                updateUserLastName(accountId, scanner, connection);
                break;
            case 'D':
                updateUserAccountNumber(accountId, scanner, connection);
                break;
            case 'E':
                break;
        }
    }

    public void updateUserFirstName(int id, Scanner scanner, Connection connection) {
        System.out.print("Enter your first name: ");
        String firstName = scanner.next();

        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.ACCOUNT_UPDATE_FIRST_NAME)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserLastName(int id, Scanner scanner, Connection connection) {
        System.out.print("Enter your last name: ");
        String lastName = scanner.next();

        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.ACCOUNT_UPDATE_LAST_NAME)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserAccountNumber(int id, Scanner scanner, Connection connection) {
        System.out.print("Enter your new account number: ");
        int accountNumber = scanner.nextInt();

        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.ACCOUNT_UPDATE_ACCOUNT_NUMBER)) {
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Connection connection, Scanner scanner) {
        System.out.println("Enter account number from account you want to delete: (0 to Exit! )");
        int accountNumber = scanner.nextInt();

        if (accountNumber == 0) {
            return;
        } else {
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(Constant.DELETE_ACCOUNT_BASED_ON_ID)
            ) {
                if (account.isaAccountNumberAlreadyExist(accountNumber)) {
                    preparedStatement.setInt(1, accountNumber);

                    if (preparedStatement.executeUpdate() == 1)
                        System.out.println("Account is deleted!");
                    else
                        System.out.println("Something is wrong!");
                } else
                    System.out.println("Account number: \"" + accountNumber + "\" don't exist");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void displayAllRows(Connection connection) {
        try (
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(Constant.ACCOUNT_QUERY_EVERYTHING)
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
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.QUERY_ACCOUNT_BY_ACCOUNT_NUMBER)) {
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
        System.out.print("Enter account number: ");
        int accountNUmber = scanner.nextInt();

        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constant.QUERY_ACCOUNT_BY_ACCOUNT_NUMBER,
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, accountNUmber);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Account account = new Account();
                account.setAccountId(resultSet.getInt("id"));
                account.setAccountNumber(resultSet.getInt("accountNumber"));

                return account.getAccountId();
            } else
                System.out.println("Invalid account number!");
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

        User user = new User(firstLetterToUpperCase(firstName), firstLetterToUpperCase(lastName));
        account = new Account(user, accountNumber, amount);

        accountManager.insertAccount(user, account, connection);
    }

    /**
     * Return string with first character in uppercase
     */
    private String firstLetterToUpperCase(String name) {
        return Character.toString(name.charAt(0)).toUpperCase() + name.substring(1);
    }
}
