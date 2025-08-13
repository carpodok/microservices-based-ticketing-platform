package com.atc.inventory.dto;

import com.atc.inventory.entity.SeatState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {
    private Long id;
    private Long eventId;
    private String seatLabel;
    private Long tierId;
    private SeatState state;
}
