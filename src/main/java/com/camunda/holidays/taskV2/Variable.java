package com.camunda.holidays.taskV2;

/**
 * Represents a variable associated with a task.
 */
@Deprecated
public record Variable(
    String name,
    String value,
    String type
) {}