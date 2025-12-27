package com.hotelio.monolith.client;

import com.hotelio.monolith.entity.Booking;
import com.hotelio.monolith.service.BookingService;
import com.hotelio.proto.booking.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@ConditionalOnProperty(name = "use-external-booking", havingValue = "true")
public class BookingClient implements BookingService {
    private static final Logger log = LoggerFactory.getLogger(BookingClient.class);

    private final BookingServiceGrpc.BookingServiceBlockingStub stub;

    public BookingClient(@Autowired BookingServiceGrpc.BookingServiceBlockingStub stub) {
        this.stub = stub;
    }

    @Override
    public List<Booking> listAll(String userId) {
        log.info("listAll started. userId={}", userId);
        BookingListRequest.Builder requestBuilder = BookingListRequest.newBuilder();
        if (userId != null) {
            requestBuilder.setUserId(userId);
        }
        BookingListResponse response = stub.listBookings(requestBuilder.build());
        List<Booking> bookings = response.getBookingsList().stream().map(BookingClient::mapBookingResponseToBooking).toList();
        log.info("listAll returns {}", bookings);
        return bookings;
    }

    @Override
    public Booking createBooking(String userId, String hotelId, String promoCode) {
        log.info("createBooking started. userId={}, hotelId={}, promoCode={}",
                userId, hotelId, promoCode);
        BookingRequest bookingRequest = buildBookingRequest(userId, hotelId, promoCode);
        BookingResponse response = stub.createBooking(bookingRequest);
        Booking booking = mapBookingResponseToBooking(response);
        log.info("createBooking returns {}", booking);
        return booking;
    }

    private static BookingRequest buildBookingRequest(String userId, String hotelId, String promoCode) {
        BookingRequest.Builder builder = BookingRequest.newBuilder();
        if (userId != null) {
            builder.setUserId(userId);
        }
        if (hotelId != null) {
            builder.setHotelId(hotelId);
        }
        if (promoCode != null) {
            builder.setPromoCode(promoCode);
        }
        return builder.build();
    }

    private static Booking mapBookingResponseToBooking(BookingResponse bookingResponse) {
        Booking booking = new Booking();
        booking.setId(Long.valueOf(bookingResponse.getId()));
        booking.setUserId(bookingResponse.getUserId());
        booking.setHotelId(bookingResponse.getHotelId());
        booking.setPromoCode(bookingResponse.getPromoCode());
        booking.setDiscountPercent(bookingResponse.getDiscountPercent());
        booking.setPrice(bookingResponse.getPrice());
        booking.setCreatedAt(Instant.parse(bookingResponse.getCreatedAt()));
        return booking;
    }
}
