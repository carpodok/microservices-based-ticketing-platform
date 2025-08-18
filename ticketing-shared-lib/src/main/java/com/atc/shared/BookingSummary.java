package com.atc.shared;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Simplified booking representation shared across services.
 */
@Data
public class BookingSummary {
    @NotNull
    private Long bookingId;

    @NotNull
    private Long userId;

    @NotNull
    private Long eventId;

    @NotNull
    @Positive
    private BigDecimal totalAmount;

    private String currency;
}
