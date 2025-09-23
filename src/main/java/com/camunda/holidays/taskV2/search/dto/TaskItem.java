package com.camunda.holidays.taskV2.search.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public record TaskItem (
    String name,
    String state,
    String assignee,
    String elementId,
    List<String> candidateGroups,
    List<String> candidateUsers,
    String processDefinitionId,
    OffsetDateTime creationDate,
    OffsetDateTime completionDate,
    OffsetDateTime followUpDate,
    OffsetDateTime dueDate,
    String tenantId,
    String externalFormReference,
    int processDefinitionVersion,
    Map<String, Object> customHeaders,
    int priority,
    String userTaskKey,
    String elementInstanceKey,
    String processName,
    String processDefinitionKey,
    String processInstanceKey,
    String formKey
)
    {}