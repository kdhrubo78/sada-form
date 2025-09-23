package com.camunda.holidays.taskV2.complete;

import java.time.LocalDate;
import java.util.Map;

public record LeaveApplicationRequest(
        String id, String formKey,
        String name, LocalDate start, LocalDate end) {

    public Map<String, Object> variables() {
        return
        Map.of("startDate", start, "endDate", end,  "name", name);
    }
}
