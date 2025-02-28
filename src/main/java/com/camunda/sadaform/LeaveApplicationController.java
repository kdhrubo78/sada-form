package com.camunda.sadaform;

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
public class LeaveApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(LeaveApplicationController.class);

    @GetMapping
    public String apply() {
        return "apply";
    }

    @GetMapping("/confirm")
    public String confirm() {
        return "confirmation";
    }

    /**
     * Handles the form submission from the leave application form
     *
     * @param leaveDate Date selected for leave
     * @param role Role of the applicant (Employee or Manager)
     * @param redirectAttributes For flash messages
     * @return Redirects to confirmation page on success
     */
    @PostMapping
    public String handleLeaveApplication(
            @ModelAttribute("leaveDate") String leaveDate,
            @ModelAttribute("role") String role,
            RedirectAttributes redirectAttributes) {

        try {
            // Convert string date to LocalDate
            LocalDate parsedDate = LocalDate.parse(leaveDate);

            logger.info("Leave Date : " + leaveDate);

            // Create leave application object
            //LeaveApplication application = new LeaveApplication();
            // application.setLeaveDate(parsedDate);
            //application.setRole(role);

            // Save the application
            //leaveApplicationService.saveLeaveApplication(application);

            // Add success message
            redirectAttributes.addFlashAttribute("successMessage",
                    "Leave application submitted successfully for " + parsedDate);

            return "redirect:/apply/confirm";

        } catch (Exception e) {
            // Log the error
            System.err.println("Error processing leave application: " + e.getMessage());

            // Add error message
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error processing your application. Please try again.");

            return "redirect:/apply";
        }
    }
}
