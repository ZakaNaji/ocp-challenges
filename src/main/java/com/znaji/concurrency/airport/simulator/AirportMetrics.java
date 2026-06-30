package com.znaji.concurrency.airport.simulator;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class AirportMetrics {

    private final ConcurrentHashMap <TaskType, LongAdder> taskCountMap
            = new ConcurrentHashMap<>();

    public void taskProcessed(AirportTask task) {
        taskCountMap
                .computeIfAbsent(task.type(), k -> new LongAdder())
                .increment();
    }

    public long countForTaskType(TaskType type) {
        LongAdder adder = taskCountMap.get(type);
        return adder != null ? adder.sum() : 0L;
    }

    public Map<TaskType, Long> snapshot() {
        Map<TaskType, Long> snapshot = new EnumMap<>(TaskType.class);

        for (Map.Entry<TaskType, LongAdder> entry : taskCountMap.entrySet()) {
            snapshot.put(entry.getKey(), entry.getValue().sum());
        }
        return snapshot;
    }
}
