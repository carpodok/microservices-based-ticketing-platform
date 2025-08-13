package com.atc.booking.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingItemDto {
    private Long id;
    private String seatLabel;
    private Long tierId;
    private BigDecimal priceAtBooking;
}
