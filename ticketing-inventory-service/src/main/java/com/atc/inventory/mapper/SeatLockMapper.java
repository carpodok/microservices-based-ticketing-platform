package com.atc.inventory.mapper;

import com.atc.inventory.dto.SeatLockDto;
import com.atc.inventory.entity.SeatLock;
import org.springframework.stereotype.Component;

@Component
public class SeatLockMapper {
    public SeatLockDto toDto(SeatLock lock) {
        if (lock == null) {
            return null;
        }
        return new SeatLockDto(
                lock.getId(),
                lock.getEventId(),
                lock.getSeatLabel(),
                lock.getLockedUntil(),
                lock.getBookingRef(),
                lock.getLockedByUser()
        );
    }
}
