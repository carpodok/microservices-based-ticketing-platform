package com.atc.catalog.controller;

import com.atc.catalog.dto.EventDto;
import com.atc.catalog.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public Page<EventDto> getAllEvents(
            @RequestParam Optional<String> city,
            @RequestParam Optional<OffsetDateTime> from,
            @RequestParam Optional<OffsetDateTime> to,
            Pageable pageable
    ) {
        return eventService.getAllEvents(city, from, to, pageable);
    }

    @GetMapping("/{eventId}")
    public EventDto getEventById(@PathVariable Long eventId) {
        return eventService.getEventById(eventId);
    }
}
