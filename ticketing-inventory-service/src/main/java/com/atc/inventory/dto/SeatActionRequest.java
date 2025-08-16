package com.atc.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SeatActionRequest {
    @NotNull
    private Long eventId;

    @NotEmpty
    private List<@NotBlank String> seatLabels;
}
