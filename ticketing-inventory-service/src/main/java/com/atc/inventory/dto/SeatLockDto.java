package com.atc.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatLockDto {
    private Long id;
    private Long eventId;
    private String seatLabel;
    private OffsetDateTime lockedUntil;
    private String bookingRef;
    private Long lockedByUser;
}
