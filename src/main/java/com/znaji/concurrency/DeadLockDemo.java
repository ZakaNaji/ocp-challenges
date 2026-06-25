package com.znaji.concurrency;

public class DeadLockDemo {
    public static void main(String[] args) {
        Account account1 = new Account(1, 1000, "Alice");
        Account account2 = new Account(2, 2000, "Bob");

        TransferService transferService = new TransferService();

        Thread thread1 = new Thread(() -> {
            TransferRequest request = new TransferRequest(account1, account2, 100);
            boolean status = transferService.transferWithRetry(request, 3);
            if (status) {
                System.out.println("Transfer from account1 to account2 succeeded.");
            } else {
                System.out.println("Transfer from account1 to account2 failed after retries.");
            }
        });

        Thread thread2 = new Thread(() -> {
            TransferRequest request = new TransferRequest(account2, account1, 200);
            boolean status = transferService.transferWithRetry(request, 3);
            if (status) {
                System.out.println("Transfer from account2 to account1 succeeded.");
            } else {
                System.out.println("Transfer from account2 to account1 failed after retries.");
            }
        });

        thread1.start();
        thread2.start();
    }
}
