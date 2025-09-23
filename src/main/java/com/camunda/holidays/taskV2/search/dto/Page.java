package com.camunda.holidays.taskV2.search.dto;

public record Page (
    int totalItems,
    boolean hasMoreTotalItems
)
    {}