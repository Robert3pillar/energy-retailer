package com.vistra.energyretailer.database.repositories;

import com.vistra.energyretailer.database.model.UnitMarketDesignation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitMarketDesignationRepository extends JpaRepository<UnitMarketDesignation, Long> {

}
