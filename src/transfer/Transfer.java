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

    public Account getSourceTarget() {
        return sourceTarget;
    }

    public void setSourceTarget(Account sourceTarget) {
        this.sourceTarget = sourceTarget;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }

    public double getAmountToTransfer() {
        return amountToTransfer;
    }

    public void setAmountToTransfer(double amountToTransfer) {
        this.amountToTransfer = amountToTransfer;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "sourceTarget=" + sourceTarget +
                ", targetAccount=" + targetAccount +
                ", amountToTransfer=" + amountToTransfer +
                '}';
    }
}
