package com.atc.catalog.repository;

import com.atc.catalog.dto.HallDto;
import com.atc.catalog.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallDao extends JpaRepository<Hall, Long> {

    public List<HallDto> findAllByVenueId(Long venueId);
}
