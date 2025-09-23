package com.camunda.holidays.taskV2.form;

import com.camunda.holidays.auth.AuthResponse;
import com.camunda.holidays.auth.AuthService;
import com.camunda.holidays.taskV2.complete.CompleteUserTasksRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class GetUserTaskFormClient {

    private static final Logger logger = LoggerFactory.getLogger(GetUserTaskFormClient.class);

    @Value("${C8_CLUSTER_ID}")
    private String CLUSTER_ID;

    @Value("${C8_CLUSTER_REGION}")
    private String CLUSTER_REGION;

    @Value("${cluster.base-url}")
    private String CLUSTER_BASE_URL;

    private final AuthService authService;

    public GetUserTaskFormClient(AuthService authService) {
        this.authService = authService;
    }

    public String findByUserTaskKey(String userTaskKey) {

        AuthResponse authResponse = authService.authenticate();

        RestClient restClient = RestClient.create();

        String endpoint = CLUSTER_BASE_URL + "user-tasks/" + userTaskKey + "/form";

        logger.info("Complete user task endpoint : {}" , endpoint );


        ResponseEntity<UserTaskFormResponse> response = restClient.get()
                .uri(endpoint)
                .header("Authorization", "Bearer " + authResponse.accessToken())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(UserTaskFormResponse.class);

        logger.debug("response: {}", response);

        if(!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusCode() + " , reason: " + response.getBody());
        }

        return response.getBody().schema();

    }

}
