package com.camunda.holidays.taskV2.form;

public record Layout(
    String row,
    Object columns // Using Object, since columns can be null or another type; adjust if type becomes known
) {}