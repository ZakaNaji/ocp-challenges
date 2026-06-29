package com.znaji.concurrency.account.transfer.simulator;

import java.util.concurrent.locks.Lock;

public class Account {
    private final long id;
    private long balance;
    private final String owner;
    private final Lock lock;

    public Account(long id, long balance, String owner) {
        this.id = id;
        this.balance = balance;
        this.owner = owner;
        this.lock = new java.util.concurrent.locks.ReentrantLock();
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public void withdraw(long amount) {
        balance -= amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account account = (Account) obj;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    public long getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    public Lock getLock() {
        return lock;
    }
}
