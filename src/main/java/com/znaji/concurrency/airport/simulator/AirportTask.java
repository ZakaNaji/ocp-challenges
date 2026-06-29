package com.znaji.concurrency.airport.simulator;

public record AirportTask(
        String id,
        TaskType type,
        TaskPriority priority,
        String flightCode
) {}
