package com.atc.catalog.mapper;

import com.atc.catalog.dto.EventDto;
import com.atc.catalog.entity.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDto toDto(Event event);
    List<EventDto> toDtoList(List<Event> events);
}
