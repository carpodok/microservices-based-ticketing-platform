package com.atc.catalog.repository;

import com.atc.catalog.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface EventDao extends JpaRepository<Event, Long> {

    Page<Event> findByStartAtBetweenAndHallVenueCityIgnoreCase(OffsetDateTime from,
                                                               OffsetDateTime to,
                                                               String city,
                                                               Pageable pageable);
}
