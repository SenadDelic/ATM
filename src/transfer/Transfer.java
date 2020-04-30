package transfer;

public class Transfer {
    private int id;
    private int sourceTarget;
    private int targetAccount;
    private double amountToTransfer;

    public Transfer() {
    }

    public Transfer(int sourceTarget, int targetAccount, double amountToTransfer) {
        this.sourceTarget = sourceTarget;
        this.targetAccount = targetAccount;
        this.amountToTransfer = amountToTransfer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSourceTarget() {
        return sourceTarget;
    }

    public void setSourceTarget(int sourceTarget) {
        this.sourceTarget = sourceTarget;
    }

    public int getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(int targetAccount) {
        this.targetAccount = targetAccount;
    }

    public double getAmountToTransfer() {
        return amountToTransfer;
    }

    public void setAmountToTransfer(double amountToTransfer) {
        this.amountToTransfer = amountToTransfer;
    }
}
