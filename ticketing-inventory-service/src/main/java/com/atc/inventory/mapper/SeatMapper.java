package com.atc.inventory.mapper;

import com.atc.inventory.dto.SeatDto;
import com.atc.inventory.entity.EventSeat;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {
    public SeatDto toDto(EventSeat seat) {
        if (seat == null) {
            return null;
        }
        return new SeatDto(
                seat.getId(),
                seat.getEventId(),
                seat.getSeatLabel(),
                seat.getTierId(),
                seat.getState()
        );
    }
}
