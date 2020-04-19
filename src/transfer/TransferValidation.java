package transfer;

import account.Account;

public class TransferValidation {
    private Account account;

    public TransferValidation() {
        account = new Account();
    }

    public boolean isEnoughMoney(Account sourceAccount, double amountToTransfer) {
        return sourceAccount.getAmount() < amountToTransfer;
    }

    public boolean isSourceAccountValid(int sourceAccount) {
        for (int i = 0; i < account.getListOfAccounts().size(); i++) {
            if (account.getListOfAccounts().get(i).getAccountNumber() == sourceAccount)
                return false;
        }
        return true;
    }

    public boolean isTargetAccountValid(int targetAccount) {
        for (int i = 0; i < account.getListOfAccounts().size(); i++) {
            if (account.getListOfAccounts().get(i).getAccountNumber() == targetAccount)
                return false;
        }
        return true;
    }

    public void moneyTransfer(Account sourceAccount, Account targetAccount, double amountToTransfer) {
        sourceAccount.setAmount(sourceAccount.getAmount() - amountToTransfer);
        targetAccount.setAmount(targetAccount.getAmount() + amountToTransfer);
        System.out.println("Money is transferred");
    }
}
