package com.znaji.concurrency;

public class DeadLockDemo {
    public static void main(String[] args) {
        Account account1 = new Account(1, 1000, "Alice");
        Account account2 = new Account(2, 2000, "Bob");

        TransferService transferService = new TransferService();

        Thread thread1 = new Thread(() -> {
            TransferRequest request = new TransferRequest(account1, account2, 100);
            transferService.transfer(request);
            System.out.println("Transfer from Alice to Bob completed.");
        });

        Thread thread2 = new Thread(() -> {
            TransferRequest request = new TransferRequest(account2, account1, 200);
            transferService.transfer(request);
            System.out.println("Transfer from Bob to Alice completed.");
        });

        thread1.start();
        thread2.start();
    }
}
