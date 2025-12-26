package com.hotelio.booking.client;

import com.hotelio.booking.dto.PromoCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PromoCodeClient {
    private final RestClient client;

    public PromoCodeClient(@Autowired RestClient.Builder builder,
                        @Value("${hotelio.service.promo.host}") String host,
                        @Value("${hotelio.service.promo.port}") String port) {
        this.client = builder.baseUrl("http://" + host + ":" + port + "/api/promos").build();
    }
    public PromoCode validate(String promoCode, String userId) {
        return client.post()
                .uri(UriComponentsBuilder.fromUriString("/validate")
                        .queryParam("code", promoCode)
                        .queryParam("userId", userId)
                        .toUriString()
                )
                .retrieve()
                .body(PromoCode.class);
    }
}
