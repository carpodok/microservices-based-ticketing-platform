package com.atc.catalog.service;

import com.atc.catalog.dto.VenueDto;
import com.atc.catalog.entity.Venue;
import com.atc.catalog.mapper.VenueMapper;
import com.atc.catalog.repository.VenueDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {

    private final VenueDao venueDao;
    private final VenueMapper venueMapper;

    public VenueService(VenueDao venueDao, VenueMapper venueMapper) {
        this.venueDao = venueDao;
        this.venueMapper = venueMapper;
    }

    public List<VenueDto> getAllVenues() {
        List<Venue> venues = venueDao.findAll();
        return venueMapper.toDtoList(venues);
    }
}
