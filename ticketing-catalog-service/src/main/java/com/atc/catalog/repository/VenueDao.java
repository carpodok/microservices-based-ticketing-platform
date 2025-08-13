package com.atc.catalog.repository;

import com.atc.catalog.dto.VenueDto;
import com.atc.catalog.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueDao  extends JpaRepository<Venue,Long > {
}
