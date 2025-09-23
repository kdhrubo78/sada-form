package com.camunda.holidays.taskV2.complete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/apply")
public class ApplyLeaveController {

    private static final Logger logger = LoggerFactory.getLogger(ApplyLeaveController.class);

    private final CompleteUserTaskClient  completeUserTaskClient;

    public ApplyLeaveController(CompleteUserTaskClient completeUserTaskClient) {
        this.completeUserTaskClient = completeUserTaskClient;
    }

    @GetMapping
    public String apply() {
        return "apply";
    }

    @GetMapping("/confirm")
    public String confirm() {
        return "confirmation";
    }


    @PostMapping
    public String submit(
            @ModelAttribute("leaveDate") LeaveApplicationRequest leaveApplicationRequest,
            RedirectAttributes redirectAttributes) {

        try {

            logger.info("Leave application : {}" , leaveApplicationRequest);

            completeUserTaskClient.complete(leaveApplicationRequest.id(), leaveApplicationRequest.variables());

            logger.info("Application completed.");

            // Add success message
            redirectAttributes.addFlashAttribute("successMessage",
                    "Leave application submitted successfully for ");

            return "redirect:/apply/confirm";

        } catch (Exception e) {
            // Log the error
            logger.error("Error processing leave application: " , e);

            // Add error message
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error processing your application. Please try again.");

            return "redirect:/apply?id=" + leaveApplicationRequest.id() + "&formKey=" + leaveApplicationRequest.formKey();
        }
    }
}
