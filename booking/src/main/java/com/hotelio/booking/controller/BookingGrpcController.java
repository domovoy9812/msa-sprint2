package com.hotelio.booking.controller;

import com.hotelio.booking.data.entity.Booking;
import com.hotelio.booking.service.BookingService;
import com.hotelio.proto.booking.*;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingGrpcController extends BookingServiceGrpc.BookingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BookingGrpcController.class);
    private final BookingService bookingService;

    public BookingGrpcController(@Autowired BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void createBooking(BookingRequest request, StreamObserver<BookingResponse> responseObserver) {
        try {
            log.info("createBooking started request={}", request);
            Booking booking = bookingService.createBooking(request.getUserId(), request.getHotelId(), request.getPromoCode());
            log.info("bookingService.createBooking returns {}", booking);
            BookingResponse response = buildBookingResponse(booking);
            log.info("createBooking returns {}", response);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Throwable throwable) {
            log.error("createBooking failed!", throwable);
            responseObserver.onError(throwable);
        }
    }

    @Override
    public void listBookings(BookingListRequest request, StreamObserver<BookingListResponse> responseObserver) {
        try {
            log.info("listBookings started request={}", request);
            List<Booking> bookings = bookingService.listAll(request.getUserId());
            log.info("bookingService.listAll returns {}", bookings);
            BookingListResponse.Builder responseBuilder = BookingListResponse.newBuilder();
            bookings.stream().map(BookingGrpcController::buildBookingResponse).forEach(responseBuilder::addBookings);
            BookingListResponse response = responseBuilder.build();
            log.info("listBookings returns {}", response);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Throwable throwable) {
            log.error("listBookings failed!", throwable);
            responseObserver.onError(throwable);
        }
    }

    private static BookingResponse buildBookingResponse(Booking booking) {
        BookingResponse.Builder responseBuilder = BookingResponse.newBuilder();
        responseBuilder
                .setId(String.valueOf(booking.getId()))
                .setUserId(booking.getUserId())
                .setHotelId(booking.getHotelId())
                .setDiscountPercent(booking.getDiscountPercent())
                .setPrice(booking.getPrice())
                .setCreatedAt(booking.getCreatedAt().toString());
        if (booking.getPromoCode() != null) {
            responseBuilder.setPromoCode(booking.getPromoCode());
        }
        return responseBuilder.build();
    }
}
