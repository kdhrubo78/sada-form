package com.camunda.sadaform;

import io.camunda.zeebe.client.ZeebeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class CreateProcessInstanceCommandLineRunner implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(CreateProcessInstanceCommandLineRunner.class);

    private ZeebeClient zeebeClient;
    private TaskListService taskListService;

    public CreateProcessInstanceCommandLineRunner(ZeebeClient zeebeClient, TaskListService taskListService) {
        this.zeebeClient = zeebeClient;
        this.taskListService = taskListService;
    }

    @Override
    public void run(String... args) throws Exception {

        try  {

            logger.info("Joining.....{}", zeebeClient.newTopologyRequest().send().join());

            //client.newTopologyRequest().send().join();

            logger.info("Joined....");

            logger.info("Starting process instance.....");


            zeebeClient.newCreateInstanceCommand().bpmnProcessId("Process_1oqoa9l")
                    .version(-1)
                    .variables(Map.of("leaveDate", new Date(), "role", "manager")) //employee
                    .send().join();

            logger.info("Started process instance.....");

            //List<UserTask> userTasks =
            //zeebeClient.newUserTaskQuery().send().join()

            //logger.info("Retrieved user tasks: {}.....", userTasks);



        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }




}
