package com.znaji.concurrency.airport.simulator;

public class AirportWorker implements Runnable {

    private final String workerId;
    private final AirportTaskQueue taskQueue;
    private final AirportMetrics metrics;

    public AirportWorker(String workerId, AirportTaskQueue taskQueue, AirportMetrics metrics) {
        this.workerId = workerId;
        this.taskQueue = taskQueue;
        this.metrics = metrics;
    }


    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                AirportTask task = taskQueue.takeTask();
                processTask(task);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Worker " + workerId + " has stopped.");
    }

    private void processTask(AirportTask task) throws InterruptedException {
        System.out.println(workerId + " is processing task: " + task);
        // Simulate task processing time
        Thread.sleep(100); // Simulate time taken to process the task

        // Update metrics after processing the task
        metrics.taskProcessed(task);
    }
}
