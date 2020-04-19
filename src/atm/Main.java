package atm;

import account.Account;
import account.User;
import database.AccountManager;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        User senad = new User("Senad", "Delic");
        /*Account senadAcc = new Account(senad, 1, 300);

        if (AccountManager.insert(senad, senadAcc))
            System.out.println("New row with primary key " + senadAcc.getAccountId() + " are inserted!");*/

        // update

        int accountId;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter accountId");
        accountId = input.nextInt();

        Account account = AccountManager.getRow(accountId);
        System.out.println("Enter amount: ");
        double amount = input.nextDouble();
        User user = new User();

        AccountManager.update(senad,account);

    }
}
