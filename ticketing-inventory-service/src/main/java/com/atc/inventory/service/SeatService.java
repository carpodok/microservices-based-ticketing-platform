package com.atc.inventory.service;

import com.atc.inventory.dto.SeatActionRequest;
import com.atc.shared.SeatLockRequest;
import com.atc.inventory.entity.EventSeat;
import com.atc.inventory.entity.SeatLock;
import com.atc.inventory.entity.SeatState;
import com.atc.inventory.repository.SeatLockRepository;
import com.atc.inventory.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final SeatLockRepository seatLockRepository;

    public List<EventSeat> listSeats(Long eventId) {
        return seatRepository.findByEventId(eventId);
    }

    public List<EventSeat> lockSeats(SeatLockRequest request) {
        OffsetDateTime expiry = OffsetDateTime.now().plusSeconds(request.getLockDurationSeconds());
        List<EventSeat> updated = new ArrayList<>();
        for (String label : request.getSeatLabels()) {
            EventSeat seat = seatRepository.findByEventIdAndSeatLabel(request.getEventId(), label)
                    .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
            if (seat.getState() != SeatState.FREE) {
                throw new IllegalStateException("Seat not available for locking");
            }
            seat.setState(SeatState.LOCKED);
            seatRepository.save(seat);

            SeatLock lock = SeatLock.builder()
                    .eventId(request.getEventId())
                    .seatLabel(label)
                    .lockedUntil(expiry)
                    .bookingRef(request.getBookingRef())
                    .lockedByUser(request.getLockedByUser())
                    .build();
            seatLockRepository.save(lock);
            updated.add(seat);
        }
        return updated;
    }

    public List<EventSeat> releaseSeats(SeatActionRequest request) {
        List<EventSeat> updated = new ArrayList<>();
        for (String label : request.getSeatLabels()) {
            EventSeat seat = seatRepository.findByEventIdAndSeatLabel(request.getEventId(), label)
                    .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
            seat.setState(SeatState.FREE);
            seatRepository.save(seat);
            seatLockRepository.deleteByEventIdAndSeatLabel(request.getEventId(), label);
            updated.add(seat);
        }
        return updated;
    }

    public List<EventSeat> sellSeats(SeatActionRequest request) {
        List<EventSeat> updated = new ArrayList<>();
        for (String label : request.getSeatLabels()) {
            EventSeat seat = seatRepository.findByEventIdAndSeatLabel(request.getEventId(), label)
                    .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
            seat.setState(SeatState.SOLD);
            seatRepository.save(seat);
            seatLockRepository.deleteByEventIdAndSeatLabel(request.getEventId(), label);
            updated.add(seat);
        }
        return updated;
    }
}
