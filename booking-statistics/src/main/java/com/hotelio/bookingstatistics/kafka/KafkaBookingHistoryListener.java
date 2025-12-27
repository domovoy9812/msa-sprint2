package com.hotelio.bookingstatistics.kafka;

import com.hotelio.bookingstatistics.service.BookingHistoryService;
import com.hotelio.shared.dto.BookingHistory;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaBookingHistoryListener {
    private final BookingHistoryService bookingHistoryService;

    public KafkaBookingHistoryListener(BookingHistoryService bookingHistoryService) {
        this.bookingHistoryService = bookingHistoryService;
    }

    @KafkaListener(
            topics = "booking-history-topic",
            groupId = "booking-history-processor",
            containerFactory = "kafkaListenerContainerFactory")
    public void processHistoryRecord(BookingHistory bookingHistory, Acknowledgment acknowledgment) {
        bookingHistoryService.addBookingHistoryRecord(bookingHistory);
        acknowledgment.acknowledge();
    }
}
