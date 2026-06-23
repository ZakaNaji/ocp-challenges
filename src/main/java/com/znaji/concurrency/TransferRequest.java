package com.znaji.concurrency;

public class TransferRequest {
    private final Account fromAccount;
    private final Account toAccount;
    private final long amount;

    public TransferRequest(Account fromAccount, Account toAccount, long amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public long getAmount() {
        return amount;
    }
}
