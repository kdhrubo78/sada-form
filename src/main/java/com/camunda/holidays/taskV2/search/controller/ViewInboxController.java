package com.camunda.holidays.taskV2.search.controller;

import com.camunda.holidays.taskV2.search.SearchUserTaskClient;
import com.camunda.holidays.taskV2.search.dto.Filter;
import com.camunda.holidays.taskV2.search.dto.Pagination;
import com.camunda.holidays.taskV2.search.dto.SearchUserTasksRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewInboxController {

    private static final Logger logger = LoggerFactory.getLogger(ViewInboxController.class);

    private SearchUserTaskClient searchUserTaskClient;

    public ViewInboxController(SearchUserTaskClient searchUserTaskClient) {
        this.searchUserTaskClient = searchUserTaskClient;
    }

    @GetMapping({"","/"})
    public String list(Model model) {

        Pagination pagination = new Pagination(0,10);
        Filter filter = new Filter("CREATED");
        //Filter filter = new Filter("COMPLETED");

        var taskList = searchUserTaskClient.findAll(new SearchUserTasksRequest(pagination, filter));

        model.addAttribute("taskList",taskList);

        return "home";
    }


}
