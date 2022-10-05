package com.vistra.energyretailer.database.repositories;

import com.vistra.energyretailer.database.model.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitTypeRepository extends JpaRepository<UnitType, Long> {

}
