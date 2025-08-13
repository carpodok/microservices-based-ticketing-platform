package com.atc.booking.dto;

import com.atc.booking.entity.BookingStatus;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingDto {
    private Long id;
    private Long userId;
    private Long eventId;
    private BookingStatus status;
    private BigDecimal totalAmount;
    private String currency;
    private OffsetDateTime createdAt;
    private List<BookingItemDto> items;
}
