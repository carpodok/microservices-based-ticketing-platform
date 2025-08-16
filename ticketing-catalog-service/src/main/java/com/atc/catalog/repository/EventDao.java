package com.atc.catalog.repository;

import com.atc.catalog.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface EventDao extends JpaRepository<Event, Long> {

    @Query(value = "SELECT e.* FROM event e " +
            "JOIN hall h ON e.hall_id = h.id " +
            "JOIN venue v ON h.venue_id = v.id " +
            "WHERE e.start_at BETWEEN :from AND :to " +
            "AND LOWER(v.city) = LOWER(:city)",
            countQuery = "SELECT COUNT(*) FROM event e " +
                    "JOIN hall h ON e.hall_id = h.id " +
                    "JOIN venue v ON h.venue_id = v.id " +
                    "WHERE e.start_at BETWEEN :from AND :to " +
                    "AND LOWER(v.city) = LOWER(:city)",
            nativeQuery = true)
    Page<Event> findByStartAtBetweenAndHallVenueCityIgnoreCase(@Param("from") OffsetDateTime from,
                                                               @Param("to") OffsetDateTime to,
                                                               @Param("city") String city,
                                                               Pageable pageable);
}
