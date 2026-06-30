package com.znaji.concurrency.airport.simulator;

public class AirportSimulator {
    public static void main(String[] args) throws InterruptedException {

        AirportTaskQueue queue = new AirportTaskQueue(10);
        AirportMetrics metrics = new AirportMetrics();

        Thread worker1 = Thread.ofVirtual().start(new AirportWorker("Worker-1", queue, metrics));
        Thread worker2 = Thread.ofVirtual().start(new AirportWorker("Worker-2", queue, metrics));
        Thread worker3 = Thread.ofVirtual().start(new AirportWorker("Worker-3", queue, metrics));
        // add tasks to the queue
        for (int i = 0; i < 20; i++) {
            AirportTask task = new AirportTask(
                    "task-" + i,
                    TaskType.values()[i % TaskType.values().length],
                    TaskPriority.values()[i % TaskPriority.values().length],
                    "flight-" + (i % 5)
            );
            queue.submitTask(task);
        }

        // Wait for a while to let workers process tasks
        Thread.sleep(5000);

        // Interrupt workers to stop them
        worker1.interrupt();
        worker2.interrupt();
        worker3.interrupt();

        worker1.join();
        worker2.join();
        worker3.join();

        // Print metrics
        System.out.println(metrics.snapshot());
    }

    private static void deterministicProducerConsumerExample(AirportTaskQueue queue) throws InterruptedException {
        AirportTask normalA = new AirportTask("A", TaskType.BOARDING, TaskPriority.NORMAL, "F1");
        AirportTask normalB = new AirportTask("B", TaskType.BAGGAGE, TaskPriority.NORMAL, "F2");
        AirportTask urgentC = new AirportTask("C", TaskType.SECURITY, TaskPriority.URGENT, "F3");

        queue.submitTask(normalA);
        queue.submitTask(normalB);
        queue.submitTask(urgentC);

        System.out.println(queue.takeTask().id()); // C
        System.out.println(queue.takeTask().id()); // A
        System.out.println(queue.takeTask().id()); // B
    }

    private static void concurrentProducerConsumerExample(AirportTaskQueue taskQueue) throws InterruptedException {
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
