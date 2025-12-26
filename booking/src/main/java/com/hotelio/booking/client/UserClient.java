package com.hotelio.booking.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
public class UserClient {
    private final RestClient client;

    public UserClient(@Autowired RestClient.Builder builder,
                        @Value("${hotelio.service.user.host}") String host,
                        @Value("${hotelio.service.user.port}") String port) {
        this.client = builder.baseUrl("http://" + host + ":" + port + "/api/users").build();
    }
    public Boolean isUserActive(String userId) {
        return client.get()
                .uri("/{userId}/active", userId)
                .retrieve()
                .body(Boolean.class);
    }

    public Boolean isUserBlacklisted(String userId) {
        return client.get()
                .uri("/{userId}/blacklisted", userId)
                .retrieve()
                .body(Boolean.class);
    }

    public Optional<String> getUserStatus(String userId) {
        return Optional.ofNullable(
                client.get()
                        .uri("/{userId}/status", userId)
                        .retrieve()
                        .body(String.class));
    }
}
