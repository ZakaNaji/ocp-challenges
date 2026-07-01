package com.znaji.concurrency.airport.simulator;

public class AirportWorker implements Runnable {

    private final String workerId;
    private final AirportTaskQueue taskQueue;
    private final AirportMetrics metrics;
    private final AirportEventPublisher eventPublisher;

    public AirportWorker(String workerId, AirportTaskQueue taskQueue, AirportMetrics metrics, AirportEventPublisher eventPublisher) {
        this.workerId = workerId;
        this.taskQueue = taskQueue;
        this.metrics = metrics;
        this.eventPublisher = eventPublisher;
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
        System.out.println(workerId + " has stopped.");
    }

    private void processTask(AirportTask task) throws InterruptedException {
        System.out.println(workerId + " is processing task: " + task);
        // Simulate task processing time
        Thread.sleep(100); // Simulate time taken to process the task

        // Update metrics after processing the task
        metrics.taskProcessed(task);

        // Notify listeners that a task has been processed
        eventPublisher.publishTaskProcessed(task, workerId);
    }
}
