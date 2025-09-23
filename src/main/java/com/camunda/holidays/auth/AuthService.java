package com.camunda.holidays.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Service
public class AuthService {

    @Value("${zeebe.auth-server}")
    private String zeebeAuthServer;

    @Value("${zeebe.client-id}")
    private String clientId;

    @Value("${zeebe.client-secret}")
    private String clientSecret;

    public AuthResponse authenticate() {
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
                .body(AuthResponse.class);
    }
}
