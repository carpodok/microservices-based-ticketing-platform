package com.atc.inventory.repository;

import com.atc.inventory.entity.EventSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<EventSeat, Long> {
    List<EventSeat> findByEventId(Long eventId);
    Optional<EventSeat> findByEventIdAndSeatLabel(Long eventId, String seatLabel);
}
