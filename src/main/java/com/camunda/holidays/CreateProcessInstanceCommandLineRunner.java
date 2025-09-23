package com.camunda.holidays;

import com.camunda.holidays.taskV2.search.SearchUserTaskClient;
import io.camunda.zeebe.client.ZeebeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import java.util.Date;
import java.util.Map;

//@Component
public class CreateProcessInstanceCommandLineRunner implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(CreateProcessInstanceCommandLineRunner.class);

    private ZeebeClient zeebeClient;
    private SearchUserTaskClient searchUserTaskClient;

    public CreateProcessInstanceCommandLineRunner(ZeebeClient zeebeClient, SearchUserTaskClient searchUserTaskClient) {
        this.zeebeClient = zeebeClient;
        this.searchUserTaskClient = searchUserTaskClient;
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
