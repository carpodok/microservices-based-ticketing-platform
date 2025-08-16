package com.atc.booking.controller;

import com.atc.booking.dto.BookingDto;
import com.atc.booking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @Operation(summary = "Start a booking and lock seats")
    public BookingDto startBooking(@Valid @RequestBody BookingDto request) {
        return bookingService.startBooking(request);
    }

    @PostMapping("/{id}/confirm")
    @Operation(summary = "Confirm payment and mark seats sold")
    public BookingDto confirmBooking(@PathVariable Long id) {
        return bookingService.confirmBooking(id);
    }

    @PostMapping("/{id}/cancel")
    @Operation(summary = "Cancel booking and release seats")
    public BookingDto cancelBooking(@PathVariable Long id) {
        return bookingService.cancelBooking(id);
    }
}
