package account;

import database.AccountManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Account {
    private ArrayList<Account> listOfAccounts;
    private User user;
    private int accountId;
    private int accountNumber;
    private double amount;

    public Account() {
        this.listOfAccounts = new ArrayList<>();
        this.user = new User();
    }

    public Account(User user, int accountNumber, double amount) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.amount = amount;

        listOfAccounts = new ArrayList<>();
        listOfAccounts.add(this);
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isAccountNumberNegative(int accountNumber) {
        return accountNumber < 0;
    }

    public boolean isaAccountNumberAlreadyExist(int accountNumber, Connection connection) throws SQLException {
        AccountManager accountManager = new AccountManager();
        return accountManager.getRow(accountNumber, connection) != null;
    }

    public boolean isNegativeAmount(double amount) {
        return amount < 0;
    }

    public ArrayList<Account> getListOfAccounts() {
        return listOfAccounts;
    }

    public void printListOfAccounts() {
        listOfAccounts.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "Account{" +
                "user=" + user +
                ", accountId=" + accountId +
                ", accountNumber=" + accountNumber +
                ", amount=" + amount +
                '}';
    }
}
