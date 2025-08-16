package com.atc.booking.mapper;

import com.atc.booking.dto.BookingDto;
import com.atc.booking.dto.BookingItemDto;
import com.atc.booking.entity.Booking;
import com.atc.booking.entity.BookingItem;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper {

    public Booking toEntity(BookingDto dto) {
        if (dto == null) {
            return null;
        }
        return Booking.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .eventId(dto.getEventId())
                .status(dto.getStatus())
                .totalAmount(dto.getTotalAmount())
                .currency(dto.getCurrency())
                .createdAt(dto.getCreatedAt())
                .build();
    }

    public BookingItem toEntity(Booking booking, BookingItemDto dto) {
        if (dto == null) {
            return null;
        }
        return BookingItem.builder()
                .booking(booking)
                .seatLabel(dto.getSeatLabel())
                .tierId(dto.getTierId())
                .priceAtBooking(dto.getPriceAtBooking())
                .build();
    }

    public BookingDto toDto(Booking booking, List<BookingItem> items) {
        if (booking == null) {
            return null;
        }
        List<BookingItemDto> itemDtos = items == null ? Collections.emptyList() :
                items.stream().map(this::toDto).collect(Collectors.toList());
        return BookingDto.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .eventId(booking.getEventId())
                .status(booking.getStatus())
                .totalAmount(booking.getTotalAmount())
                .currency(booking.getCurrency())
                .createdAt(booking.getCreatedAt())
                .items(itemDtos)
                .build();
    }

    private BookingItemDto toDto(BookingItem item) {
        if (item == null) {
            return null;
        }
        return BookingItemDto.builder()
                .id(item.getId())
                .seatLabel(item.getSeatLabel())
                .tierId(item.getTierId())
                .priceAtBooking(item.getPriceAtBooking())
                .build();
    }
}
