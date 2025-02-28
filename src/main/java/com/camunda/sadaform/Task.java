package com.camunda.sadaform;

public record Task(
    String id,
    String name,
    String taskDefinitionId,
    String processName,
    String creationDate,
    String completionDate,
    String assignee,
    String taskState,
    String[] sortValues,
    boolean isFirst,
    String formKey,
    String formId,
    String formVersion,
    Boolean isFormEmbedded,
    String processDefinitionKey,
    String processInstanceKey,
    String tenantId,
    String dueDate,
    String followUpDate,
    String candidateGroups,
    String candidateUsers,
    Variable[] variables,
    String context,
    String implementation,
    int priority
) {
    /**
     * Returns whether this task is completed.
     * 
     * @return true if the task has a completion date, false otherwise
     */
    public boolean isCompleted() {
        return completionDate != null;
    }
    
    /**
     * Returns whether this task is assigned to someone.
     * 
     * @return true if the task has an assignee, false otherwise
     */
    public boolean isAssigned() {
        return assignee != null;
    }
}
