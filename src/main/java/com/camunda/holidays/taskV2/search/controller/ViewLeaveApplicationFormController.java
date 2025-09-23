package com.camunda.holidays.taskV2.search.controller;

import com.camunda.holidays.taskV2.form.GetUserTaskFormClient;
import com.camunda.holidays.taskV2.form.Schema;
import com.camunda.holidays.taskV2.search.SearchUserTaskClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ViewLeaveApplicationFormController {

    private static final Logger logger = LoggerFactory.getLogger(ViewLeaveApplicationFormController.class);

    private final ObjectMapper objectMapper;
    private final GetUserTaskFormClient getUserTaskFormClient;

    public ViewLeaveApplicationFormController(ObjectMapper objectMapper, GetUserTaskFormClient getUserTaskFormClient) {
        this.objectMapper = objectMapper;
        this.getUserTaskFormClient = getUserTaskFormClient;
    }


    @GetMapping("/show")
    public String review(Model model,
                       @RequestParam
                       String id, @RequestParam(required = false) String externalFormReference

    ) throws Exception {
        logger.info("Processing view for leave application: " + id);
        model.addAttribute("formKey", externalFormReference);
        model.addAttribute("id", id);

        if(StringUtils.isNotBlank(externalFormReference)) {
            return "forms/" + externalFormReference;
        }
        else{
            String schema =
            getUserTaskFormClient.findByUserTaskKey(id);

            logger.debug("schema: {}" , schema);

            Schema newSchema = objectMapper.readValue(schema, Schema.class);

            logger.info("newSchema: {}", newSchema.getComponents());

            model.addAttribute("schema", schema);

            return "forms/schemaForm";
        }

    }

}
