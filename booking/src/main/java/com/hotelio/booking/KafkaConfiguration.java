package com.hotelio.booking;

import com.hotelio.shared.dto.BookingHistory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfiguration {
    @Bean
    KafkaTemplate<String, BookingHistory> KafkaTemplate(ProducerFactory<String, BookingHistory> producerFactory) {
        var template = new KafkaTemplate<>(producerFactory);
        template.setObservationEnabled(true);
        return template;
    }
}
