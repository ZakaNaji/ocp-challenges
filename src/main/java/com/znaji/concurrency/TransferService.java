package com.znaji.concurrency;

import java.util.concurrent.TimeUnit;

public class TransferService {


    public boolean transferWithTimeout(TransferRequest request) throws InterruptedException {
        validRequest(request);

        Account fromAccount = request.getFromAccount();
        Account toAccount = request.getToAccount();
        long amount = request.getAmount();

        fromAndToAccountMustBeDifferent(fromAccount, toAccount);
        amountMustBePositive(amount);

        System.out.println("trying to lock fromAccount: " + fromAccount.getId() + " by thread: " + Thread.currentThread().getName());
        if (fromAccount.getLock().tryLock(100, TimeUnit.MILLISECONDS)) {
            try {
                transferDelay();
                System.out.println("Lock acquired on fromAccount: " + fromAccount.getId() + " by thread: " + Thread.currentThread().getName());
                System.out.println("trying to lock toAccount: " + toAccount.getId() + " by thread: " + Thread.currentThread().getName());
                if (toAccount.getLock().tryLock(100, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("Lock acquired on toAccount: " + toAccount.getId() + " by thread: " + Thread.currentThread().getName());
                        fromAccountMustHaveSufficientBalance(fromAccount, amount);
                        transferFunds(fromAccount, amount, toAccount);
                        return true;
                    } finally {
                        toAccount.getLock().unlock();
                    }
                } else {
                    System.out.println("Could not acquire lock on toAccount: " + toAccount.getId() + " by thread: " + Thread.currentThread().getName());
                    return false;
                }
            } finally {
                fromAccount.getLock().unlock();
            }
        } else {
            System.out.println("Could not acquire lock on fromAccount: " + fromAccount.getId() + " by thread: " + Thread.currentThread().getName());
            return false;
        }
    }
    public void transfer(TransferRequest request) {
        validRequest(request);

        Account fromAccount = request.getFromAccount();
        Account toAccount = request.getToAccount();
        long amount = request.getAmount();

        fromAndToAccountMustBeDifferent(fromAccount, toAccount);
        amountMustBePositive(amount);

        Account firstLock = fromAccount.getId() < toAccount.getId() ? fromAccount : toAccount;
        Account secondLock = fromAccount.getId() < toAccount.getId() ? toAccount : fromAccount;

        System.out.println("trying to lock fromAccount: " + firstLock.getId() + " by thread: " + Thread.currentThread().getName());
        firstLock.getLock().lock();
        System.out.println("Lock acquired on fromAccount: " + firstLock.getId() + " by thread: " + Thread.currentThread().getName());
        try {
            transferDelay(); // Simulate a delay to increase the chance of deadlock
            System.out.println("trying to lock toAccount: " + secondLock.getId() + " by thread: " + Thread.currentThread().getName());
            secondLock.getLock().lock();
            System.out.println("Lock acquired on toAccount: " + secondLock.getId() + " by thread: " + Thread.currentThread().getName());
            try {
                fromAccountMustHaveSufficientBalance(fromAccount, amount);
                transferFunds(fromAccount, amount, toAccount);
            } finally {
                secondLock.getLock().unlock();
            }
        } finally {
            firstLock.getLock().unlock();
        }
    }

    private void transferDelay() {
        try {
            Thread.sleep(100); // Simulate a delay in the transfer process
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void transferFunds(Account fromAccount, long amount, Account toAccount) {
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }

    private void validRequest(TransferRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Transfer request cannot be null");
        }
        if (request.getFromAccount() == null || request.getToAccount() == null) {
            throw new IllegalArgumentException("Both source and destination accounts must be provided");
        }
    }

    private void amountMustBePositive(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    private void fromAccountMustHaveSufficientBalance(Account fromAccount, long amount) {
        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance in the source account");
        }
    }

    private void fromAndToAccountMustBeDifferent(Account fromAccount, Account toAccount) {
        if (fromAccount.equals(toAccount)) {
            throw new IllegalArgumentException("Source and destination accounts must be different");
        }
    }
}
