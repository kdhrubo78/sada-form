package com.camunda.holidays.taskV2.search;

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

@Service
public class SearchUserTaskClient {

    private static final Logger logger = LoggerFactory.getLogger(SearchUserTaskClient.class);

    @Value("${C8_CLUSTER_ID}")
    private String CLUSTER_ID;

    @Value("${C8_CLUSTER_REGION}")
    private String CLUSTER_REGION;

    @Value("${cluster.base-url}")
    private String CLUSTER_BASE_URL;

    private final AuthService authService;

    public SearchUserTaskClient(AuthService authService) {
        this.authService = authService;
    }

    public List<TaskItem> findAll(SearchUserTasksRequest searchUserTasksRequest) {

        AuthResponse authResponse = authService.authenticate();

        RestClient restClient = RestClient.create();

        String endpoint = CLUSTER_BASE_URL + "user-tasks/search";

        logger.info("searchUserTasksRequest: {}", searchUserTasksRequest);

        ResponseEntity<TaskResponse> response = restClient.post()
                .uri(endpoint)
                .body(searchUserTasksRequest)
                .header("Authorization", "Bearer " + authResponse.accessToken())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(TaskResponse.class);


        return response.getBody().items();
    }

}
