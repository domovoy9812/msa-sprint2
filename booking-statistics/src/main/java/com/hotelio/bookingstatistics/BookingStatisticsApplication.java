package com.hotelio.bookingstatistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hotelio", "com.hotelio.bookingstatistics"})
public class BookingStatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookingStatisticsApplication.class, args);
    }

}
