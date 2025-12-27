package com.hotelio.bookingstatistics.data.repository;

import com.hotelio.bookingstatistics.data.entity.BookingHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingHistoryRepository extends JpaRepository<BookingHistoryEntity, Long> {
    List<BookingHistoryEntity> findByUserId(String userId);
    List<BookingHistoryEntity> findByHotelId(String hotelId);
}
