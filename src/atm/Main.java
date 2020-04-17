package atm;

import account.Account;
import account.User;
import database.AccountManager;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        User senad = new User("Senad", "Delic");
        Account senadAcc = new Account(senad, 1, 300);

        if (AccountManager.insert(senadAcc, senad))
            System.out.println("New row with primary key " + senadAcc.getAccountId() + " are inserted!");

    }
}
