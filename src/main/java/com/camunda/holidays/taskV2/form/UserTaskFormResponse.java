package com.camunda.holidays.taskV2.form;

import java.util.Map;

public record UserTaskFormResponse(String tenantId, String formId, String schema, long version, String formKey) {
}
