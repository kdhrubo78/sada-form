package com.camunda.holidays.taskV2.search.dto;

import java.util.List;

public record TaskResponse (
    List<TaskItem> items,
    Page page
){
}

