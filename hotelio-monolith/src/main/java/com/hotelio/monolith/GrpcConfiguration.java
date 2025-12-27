package com.hotelio.monolith;

import com.hotelio.proto.booking.BookingServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class GrpcConfiguration {
    @Bean
    BookingServiceGrpc.BookingServiceBlockingStub bookingServiceStub(GrpcChannelFactory channels) {
        return BookingServiceGrpc.newBlockingStub(channels.createChannel("booking"));
    }
}
