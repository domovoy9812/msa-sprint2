package com.hotelio.monolith.service;

import com.hotelio.monolith.entity.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> listAll(String userId);

    Booking createBooking(String userId, String hotelId, String promoCode);
}
