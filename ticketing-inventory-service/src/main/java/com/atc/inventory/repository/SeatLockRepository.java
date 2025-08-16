package com.atc.inventory.repository;

import com.atc.inventory.entity.SeatLock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatLockRepository extends JpaRepository<SeatLock, Long> {

    Optional<SeatLock> findByEventIdAndSeatLabel(Long eventId, String seatLabel);

    void deleteByEventIdAndSeatLabel(Long eventId, String seatLabel);
}
