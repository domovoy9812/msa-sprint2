package com.hotelio.bookingstatistics;

import com.hotelio.shared.dto.BookingHistory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
public class KafkaConfiguration {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BookingHistory> kafkaListenerContainerFactory(
            ConsumerFactory<String, BookingHistory> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, BookingHistory> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        ContainerProperties containerProperties = factory.getContainerProperties();
        containerProperties.setAckMode(ContainerProperties.AckMode.MANUAL);
        containerProperties.setObservationEnabled(true);
        return factory;
    }
}
