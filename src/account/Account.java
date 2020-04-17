package account;

import atm.User;

import java.util.Date;

public class Account {
    private User user;
    private int accountNumber;
    private double amount;
    private Date date;

    public Account() {
        this.user = new User();
        this.date = new Date();
    }

    public Account(User user, int accountNumber, double amount) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.date = new Date();
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
