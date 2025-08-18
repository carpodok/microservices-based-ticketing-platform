package com.atc.shared;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

/**
 * Request payload for locking seats across services.
 */
@Data
public class SeatLockRequest {
    @NotNull
    private Long eventId;

    @NotEmpty
    private List<@NotBlank String> seatLabels;

    @NotNull
    @Positive
    private Long lockDurationSeconds;

    private String bookingRef;

    private Long lockedByUser;
}
