package com.atc.catalog.controller;

import com.atc.catalog.dto.HallDto;
import com.atc.catalog.dto.VenueDto;
import com.atc.catalog.repository.HallDao;
import com.atc.catalog.repository.VenueDao;
import com.atc.catalog.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {

    /** Services */
    @Autowired
    VenueService venueService;


    /** Repositories */
    @Autowired
    private VenueDao venueDao;

    @Autowired
    private HallDao hallDao;

    @GetMapping
    public List<VenueDto> getAllVenues() {
        return venueService.getAllVenues();
    }

    @GetMapping("/{venueId}/halls")
    public List<HallDto> getHallsByVenue(@PathVariable Long venueId) {
        return hallDao.findAllByVenueId(venueId);
    }
}