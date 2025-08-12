package com.atc.catalog.repository;

import com.atc.catalog.entity.PriceTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceTierDao extends JpaRepository<PriceTier, Long> {
}
