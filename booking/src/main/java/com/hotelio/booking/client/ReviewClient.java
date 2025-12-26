package com.hotelio.booking.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ReviewClient {
    private final RestClient client;

    public ReviewClient(@Autowired RestClient.Builder builder,
                        @Value("${hotelio.service.review.host}") String host,
                        @Value("${hotelio.service.review.port}") String port) {
        this.client = builder.baseUrl("http://" + host + ":" + port + "/api/reviews").build();
    }

    public Boolean isTrustedHotel(String hotelId) {
        return client.get()
                .uri("/hotel/{hotelId}/trusted", hotelId)
                .retrieve()
                .body(Boolean.class);
    }
}
