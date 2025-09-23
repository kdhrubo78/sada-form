package com.camunda.holidays.taskV2.form;

public record Component(
    String label,
    String type,
    Layout layout,
    String id,
    String key,
    String description,
    // Optional fields specific to certain component types
    String subtype,
    String dateLabel,
    Boolean readonly
) {}