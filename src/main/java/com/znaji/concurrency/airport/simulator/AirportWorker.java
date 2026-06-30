package com.znaji.concurrency.airport.simulator;

public class AirportWorker implements Runnable{

    private final String workerId;
    private final AirportTaskQueue taskQueue;

    public AirportWorker(String workerId, AirportTaskQueue taskQueue) {
        this.workerId = workerId;
        this.taskQueue = taskQueue;
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

    private void processTask(AirportTask task) {
        System.out.println("Worker " + workerId + " is processing task: " + task);
        // Simulate task processing time
        try {
            Thread.sleep(100); // Simulate time taken to process the task
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
