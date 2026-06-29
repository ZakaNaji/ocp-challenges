package com.znaji.concurrency.airport.simulator;

import java.util.concurrent.BlockingDeque;

public class AirportTaskQueue {

    private final BlockingDeque<AirportTask> taskQueue;


    public AirportTaskQueue(int capacity) {
        this.taskQueue = new java.util.concurrent.LinkedBlockingDeque<>(capacity);
    }

    public void submitTask(AirportTask task) throws InterruptedException {
        if (task.priority() == TaskPriority.URGENT) {
            taskQueue.putFirst(task);
        } else {
            taskQueue.putLast(task);
        }
    }

    public AirportTask takeTask() throws InterruptedException {
        return taskQueue.takeFirst();
    }

    public int size() {
        return taskQueue.size();
    }
}
