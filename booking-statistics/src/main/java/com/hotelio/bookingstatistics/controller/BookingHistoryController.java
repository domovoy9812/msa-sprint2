package com.hotelio.bookingstatistics.controller;

import com.hotelio.bookingstatistics.service.BookingHistoryService;
import com.hotelio.shared.dto.BookingHistory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking-history")
public class BookingHistoryController {

    private final BookingHistoryService bookingHistoryService;

    public BookingHistoryController(BookingHistoryService bookingHistoryService) {
        this.bookingHistoryService = bookingHistoryService;
    }

    @GetMapping("/findByUserId")
    public List<BookingHistory> findByUserId(@RequestParam String userId) {
        return bookingHistoryService.findByUserId(userId);
    }

    @GetMapping("/findByHotelId")
    public List<BookingHistory> findByHotelId(@RequestParam String hotelId) {
        return bookingHistoryService.findByHotelId(hotelId);
    }
}
