package com.znaji.concurrency.airport.simulator;

public interface AirportEventListener {
    void onTaskProcessed(AirportTask task, String workerId);
}
