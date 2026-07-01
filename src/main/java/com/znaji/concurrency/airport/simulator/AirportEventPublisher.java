package com.znaji.concurrency.airport.simulator;

import java.util.concurrent.CopyOnWriteArrayList;

public class AirportEventPublisher {

    private final CopyOnWriteArrayList<AirportEventListener> listeners = new CopyOnWriteArrayList<>();

    public void register(AirportEventListener listener) {
        listeners.addIfAbsent(listener);
    }

    public void publishTaskProcessed(AirportTask task, String workerId) {
        for (AirportEventListener listener : listeners) {
            listener.onTaskProcessed(task, workerId);
        }
    }


}
