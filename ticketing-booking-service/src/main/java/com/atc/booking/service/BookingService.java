package com.atc.booking.service;

import com.atc.booking.dto.BookingDto;
import com.atc.booking.entity.Booking;
import com.atc.booking.entity.BookingItem;
import com.atc.booking.entity.BookingStatus;
import com.atc.booking.mapper.BookingMapper;
import com.atc.booking.repository.BookingItemRepository;
import com.atc.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingItemRepository bookingItemRepository;
    private final BookingMapper bookingMapper;

    @Transactional
    public BookingDto startBooking(BookingDto request) {
        Booking booking = bookingMapper.toEntity(request);
        booking.setStatus(BookingStatus.PENDING);
        BigDecimal total = request.getItems() == null ? BigDecimal.ZERO :
                request.getItems().stream()
                        .map(i -> i.getPriceAtBooking() == null ? BigDecimal.ZERO : i.getPriceAtBooking())
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
        booking.setTotalAmount(total);
        booking = bookingRepository.save(booking);

        Booking finalBooking = booking;
        List<BookingItem> items = request.getItems() == null ? List.of() : request.getItems().stream()
                .map(i -> bookingMapper.toEntity(finalBooking, i))
                .map(bookingItemRepository::save)
                .collect(Collectors.toList());

        // TODO: invoke inventory service via gRPC to lock seats

        return bookingMapper.toDto(booking, items);
    }

    @Transactional
    public BookingDto confirmBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        List<BookingItem> items = bookingItemRepository.findByBookingId(booking.getId());

        // TODO: invoke inventory service via gRPC to mark seats as sold

        booking.setStatus(BookingStatus.CONFIRMED);
        booking = bookingRepository.save(booking);
        return bookingMapper.toDto(booking, items);
    }

    @Transactional
    public BookingDto cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        List<BookingItem> items = bookingItemRepository.findByBookingId(booking.getId());

        // TODO: invoke inventory service via gRPC to release locked seats

        booking.setStatus(BookingStatus.CANCELLED);
        booking = bookingRepository.save(booking);
        return bookingMapper.toDto(booking, items);
    }
}
