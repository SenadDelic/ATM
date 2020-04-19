package transfer;

import account.Account;

public class Transfer {
    private Account sourceTarget;
    private Account targetAccount;
    private double amountToTransfer;

    public Transfer() {
        
    }

    public Transfer(Account sourceTarget, Account targetAccount, double amountToTransfer) {
        this.sourceTarget = sourceTarget;
        this.targetAccount = targetAccount;
        this.amountToTransfer = amountToTransfer;
    }
}
