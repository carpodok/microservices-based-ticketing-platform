package com.atc.catalog.service;

import com.atc.catalog.dto.EventDto;
import com.atc.catalog.entity.Event;
import com.atc.catalog.mapper.EventMapper;
import com.atc.catalog.repository.EventDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class EventService {

    private final EventDao eventDao;
    private final EventMapper eventMapper;

    public EventService(EventDao eventDao, EventMapper eventMapper) {
        this.eventDao = eventDao;
        this.eventMapper = eventMapper;
    }

    public Page<EventDto> getAllEvents(Optional<String> city, Optional<OffsetDateTime> from, Optional<OffsetDateTime> to, Pageable pageable) {
        if (city.isPresent() && from.isPresent() && to.isPresent()) {
            return eventDao
                    .findByStartAtBetweenAndHallVenueCityIgnoreCase(from.get(), to.get(), city.get(), pageable)
                    .map(eventMapper::toDto);
        }
        return eventDao.findAll(pageable).map(eventMapper::toDto);
    }

    public EventDto getEventById(Long eventId) {
        Event event = eventDao.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + eventId));
        return eventMapper.toDto(event);
    }
}
