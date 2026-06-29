package com.znaji.concurrency.account.transfer.simulator;

import java.util.concurrent.atomic.AtomicInteger;

public class TransferMetrics {
    private final AtomicInteger successfulTransfers = new AtomicInteger();
    private final AtomicInteger failedTransfers = new AtomicInteger();
    private final AtomicInteger totalRetries = new AtomicInteger();

    public void incrementSuccessfulTransfers() {
        successfulTransfers.incrementAndGet();
    }

    public void incrementFailedTransfers() {
        failedTransfers.incrementAndGet();
    }

    public void incrementTotalRetries() {
        totalRetries.incrementAndGet();
    }

    public int getSuccessfulTransfers() {
        return successfulTransfers.get();
    }

    public int getFailedTransfers() {
        return failedTransfers.get();
    }

    public int getTotalRetries() {
        return totalRetries.get();
    }
}
