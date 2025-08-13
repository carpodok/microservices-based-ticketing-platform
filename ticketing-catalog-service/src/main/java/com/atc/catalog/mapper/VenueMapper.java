package com.atc.catalog.mapper;

import com.atc.catalog.dto.VenueDto;
import com.atc.catalog.entity.Venue;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VenueMapper {
    VenueDto toDto(Venue venue);
    List<VenueDto> toDtoList(List<Venue> venues);
}