package com.camunda.sadaform;

/**
 * Represents a variable associated with a task.
 */
public record Variable(
    String name,
    String value,
    String type
) {}