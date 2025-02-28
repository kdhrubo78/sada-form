package com.camunda.sadaform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskListService {

    @Value("${zeebe.auth-server}")
    private String zeebeAuthServer;

    @Value("${zeebe.client-id}")
    private String clientId;

    @Value("${zeebe.client-secret}")
    private String clientSecret;


    private static final Logger logger = LoggerFactory.getLogger(TaskListService.class);

    private TokenResponse authenticate() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "client_credentials");
        formData.add("audience", "tasklist.camunda.io");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);

        RestClient restClient = RestClient
                .create(zeebeAuthServer);

        return restClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(formData)
                .retrieve()
                .body(TokenResponse.class);
    }


    public List<Task> findAll() {


        TokenResponse tokenResponse = authenticate();

        logger.info("Access token: {}", tokenResponse);

        RestClient restClient = RestClient.create();

        String url = "https://bru-2.tasklist.camunda.io/946a6bb0-5120-4b28-9b45-29b6afa6c7c9/v1/tasks/search";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("state", "CREATED");


        ResponseEntity<List<Task>> response = restClient.post()
                .uri(url)
                .body(requestBody)
                .header("Authorization", "Bearer " + tokenResponse.accessToken())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Task>>() {});


        return response.getBody();
    }

}
