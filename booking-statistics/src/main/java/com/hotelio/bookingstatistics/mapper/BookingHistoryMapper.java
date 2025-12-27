package com.hotelio.bookingstatistics.mapper;

import com.hotelio.bookingstatistics.data.entity.BookingHistoryEntity;
import com.hotelio.shared.dto.BookingHistory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingHistoryMapper {
    BookingHistory toDto(BookingHistoryEntity entity);
    List<BookingHistory> toDtoList(List<BookingHistoryEntity> entities);
    BookingHistoryEntity toEntity(BookingHistory dto);
}
