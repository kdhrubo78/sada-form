package com.camunda.sadaform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LeaveController {

    private static final Logger logger = LoggerFactory.getLogger(LeaveController.class);

    private TaskListService taskListService;

    public LeaveController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping({"","/"})
    public String list(Model model) {
        model.addAttribute(taskListService.findAll());

        return "home";
    }

    @GetMapping("/review")
    public String review(Model model,
                       @RequestParam
                       String id, @RequestParam(required = false) String formKey

    ) {
        logger.info("Processing view for leave application: " + id);
        model.addAttribute("formKey", formKey);
        model.addAttribute("id", id);
        return formKey;
    }

}
