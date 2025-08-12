package com.atc.catalog.repository;

import com.atc.catalog.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallDao extends JpaRepository<Hall, Long> {
}
