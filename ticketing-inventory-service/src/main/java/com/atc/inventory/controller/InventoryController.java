package com.atc.inventory.controller;

import com.atc.inventory.dto.SeatActionRequest;
import com.atc.inventory.dto.SeatDto;
import com.atc.inventory.dto.SeatLockRequest;
import com.atc.inventory.mapper.SeatMapper;
import com.atc.inventory.service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final SeatService seatService;
    private final SeatMapper seatMapper;

    @GetMapping("/seats")
    @Operation(summary = "List seats for an event")
    public List<SeatDto> listSeats(@RequestParam Long eventId) {
        return seatService.listSeats(eventId).stream()
                .map(seatMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/seats/lock")
    @Operation(summary = "Lock seats with an expiry")
    public List<SeatDto> lockSeats(@Valid @RequestBody SeatLockRequest request) {
        return seatService.lockSeats(request).stream()
                .map(seatMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/seats/release")
    @Operation(summary = "Release previously locked seats")
    public List<SeatDto> releaseSeats(@Valid @RequestBody SeatActionRequest request) {
        return seatService.releaseSeats(request).stream()
                .map(seatMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/seats/sell")
    @Operation(summary = "Mark seats as sold")
    public List<SeatDto> sellSeats(@Valid @RequestBody SeatActionRequest request) {
        return seatService.sellSeats(request).stream()
                .map(seatMapper::toDto)
                .collect(Collectors.toList());
    }
}
