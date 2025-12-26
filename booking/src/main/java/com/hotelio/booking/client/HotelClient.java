package com.hotelio.booking.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class HotelClient {
    private final RestClient client;

    public HotelClient(@Autowired RestClient.Builder builder,
                           @Value("${hotelio.service.hotel.host}") String host,
                           @Value("${hotelio.service.hotel.port}") String port) {
        this.client = builder.baseUrl("http://" + host + ":" + port + "/api/hotels").build();
    }
    public Boolean isHotelOperational(String hotelId) {
        return client.get()
                .uri("/{id}/operational", hotelId)
                .retrieve()
                .body(Boolean.class);
    }

    public Boolean isHotelFullyBooked(String hotelId) {
        return client.get()
                .uri("/{id}/fully-booked", hotelId)
                .retrieve()
                .body(Boolean.class);
    }
}
