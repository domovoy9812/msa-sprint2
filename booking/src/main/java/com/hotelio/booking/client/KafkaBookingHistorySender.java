package com.hotelio.booking.client;

import com.hotelio.shared.dto.BookingHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class KafkaBookingHistorySender {
    private static final Logger log = LoggerFactory.getLogger(KafkaBookingHistorySender.class);

    private final KafkaTemplate<String, BookingHistory> kafkaTemplate;

    public KafkaBookingHistorySender(KafkaTemplate<String, BookingHistory> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBooking(BookingHistory bookingHistory) {
        log.info("before event send. booking: {}", bookingHistory);
        try {
            SendResult<String, BookingHistory> result = kafkaTemplate.send("booking-history-topic", bookingHistory).get();
            log.info("after event send. booking: {}, result.getProducerRecord(): {}, result.getRecordMetadata(): {}",
                    bookingHistory, result.getProducerRecord(), result.getRecordMetadata());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error during kafka event send", e);
            throw new RuntimeException(e);
        }
    }
}
