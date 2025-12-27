package com.hotelio.bookingstatistics.service;

import com.hotelio.bookingstatistics.data.entity.BookingHistoryEntity;
import com.hotelio.bookingstatistics.data.repository.BookingHistoryRepository;
import com.hotelio.bookingstatistics.mapper.BookingHistoryMapper;
import com.hotelio.shared.dto.BookingHistory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingHistoryService {
    private final BookingHistoryRepository repository;
    private final BookingHistoryMapper mapper;

    public BookingHistoryService(BookingHistoryRepository repository,
                                 BookingHistoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void addBookingHistoryRecord(BookingHistory bookingHistory) {
        repository.save(mapper.toEntity(bookingHistory));
    }

    public List<BookingHistory> findByUserId(String userId) {
        List<BookingHistoryEntity> entities = repository.findByUserId(userId);
        return mapper.toDtoList(entities);
    }

    public List<BookingHistory> findByHotelId(String hotelId) {
        List<BookingHistoryEntity> entities = repository.findByHotelId(hotelId);
        return mapper.toDtoList(entities);
    }
}
