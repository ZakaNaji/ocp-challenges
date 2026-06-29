package com.znaji.concurrency.airport.simulator;

public class AirportSimulator {
    public static void main(String[] args) throws InterruptedException {

        AirportTaskQueue taskQueue = new AirportTaskQueue(10);

        Thread producerThread = Thread.ofVirtual().start(() -> {
            for (int i = 0; i < 20; i++) {
                AirportTask task = new AirportTask(
                        "task-" + i,
                        TaskType.values()[i % TaskType.values().length],
                        TaskPriority.values()[i % TaskPriority.values().length],
                        "flight-" + (i % 5)
                );
                try {
                    taskQueue.submitTask(task);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Producer interrupted while submitting task: " + task);
                    return;
                }
                System.out.println("Added: " + task);
            }
        });

        Thread.sleep(100); // Let the producer add some tasks before starting the consumer

        Thread consumerThread = Thread.ofVirtual().start(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    AirportTask task = taskQueue.takeTask();
                    System.out.println("Processed: " + task);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Consumer interrupted while taking task.");
                    return;
                }
            }
        });

        producerThread.join();
        consumerThread.join();
    }
}
