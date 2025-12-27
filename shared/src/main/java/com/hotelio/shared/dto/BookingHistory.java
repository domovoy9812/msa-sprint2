package com.hotelio.shared.dto;

import java.time.Instant;

public class BookingHistory {
    private String userId;
    private String hotelId;
    private String promoCode;
    private Double discountPercent;
    private Double price;
    private Instant createdAt;
    public BookingHistory() { }
    public BookingHistory(String userId, String hotelId, String promoCode, Double discountPercent,
                          Double price, Instant createdAt) {
        this.userId = userId;
        this.hotelId = hotelId;
        this.promoCode = promoCode;
        this.discountPercent = discountPercent;
        this.price = price;
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getHotelId() {
        return hotelId;
    }
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
    public String getPromoCode() {
        return promoCode;
    }
    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
    public Double getDiscountPercent() {
        return discountPercent;
    }
    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}
