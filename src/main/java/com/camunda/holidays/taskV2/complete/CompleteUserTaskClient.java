package com.camunda.holidays.taskV2.complete;

import com.camunda.holidays.auth.AuthResponse;
import com.camunda.holidays.auth.AuthService;
import com.camunda.holidays.taskV2.search.dto.SearchUserTasksRequest;
import com.camunda.holidays.taskV2.search.dto.TaskItem;
import com.camunda.holidays.taskV2.search.dto.TaskResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class CompleteUserTaskClient {

    private static final Logger logger = LoggerFactory.getLogger(CompleteUserTaskClient.class);

    @Value("${C8_CLUSTER_ID}")
    private String CLUSTER_ID;

    @Value("${C8_CLUSTER_REGION}")
    private String CLUSTER_REGION;

    @Value("${cluster.base-url}")
    private String CLUSTER_BASE_URL;

    private final AuthService authService;

    public CompleteUserTaskClient(AuthService authService) {
        this.authService = authService;
    }

    public void complete(String userTaskKey, Map<String, Object> variables) {

        AuthResponse authResponse = authService.authenticate();

        RestClient restClient = RestClient.create();

        String endpoint = CLUSTER_BASE_URL + "user-tasks/" + userTaskKey + "/completion";

        logger.info("Complete user task endpoint : {}" , endpoint );

        logger.info("variables: {}", variables);

        ResponseEntity<Void> response = restClient.post()
                .uri(endpoint)
                .body(new CompleteUserTasksRequest(variables))
                .header("Authorization", "Bearer " + authResponse.accessToken())
                .header("Content-Type", "application/json")
                .retrieve()
                .toBodilessEntity();

        logger.info("response: {}", response);

        if(!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusCode() + " , reason: " + response.getBody());
        }


    }

}
