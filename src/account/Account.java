package account;

import java.util.ArrayList;
import java.util.Date;

public class Account {
    private ArrayList<Account> listOfAccounts;
    private User user;
    private int accountNumber;
    private double amount;
    private Date date;

    public Account() {
        listOfAccounts = new ArrayList<>();
        this.user = new User();
        this.date = new Date();
    }

    public Account(User user, int accountNumber, double amount) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.date = new Date();

        listOfAccounts = new ArrayList<>();
        listOfAccounts.add(this);
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

    public boolean isAccountNumberAlreadyExist(int accountNumber) {
        for (Account account : listOfAccounts)
            if (account.getAccountNumber() == accountNumber)
                return true;
        return false;
    }

    public boolean isAccountNumberNegative(int accountNumber) {
        return accountNumber < 0;
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
                ", accountNumber=" + accountNumber +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
