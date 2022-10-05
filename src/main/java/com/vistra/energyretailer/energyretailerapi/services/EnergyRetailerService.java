package com.vistra.energyretailer.energyretailerapi.services;

import com.vistra.energyretailer.database.model.Unit;
import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDateDto;

import java.util.List;

public interface EnergyRetailerService {

    /**
     * Saves a Market Designation
     * @return true if transaction is successful
     */
    public boolean saveUnitMarketDesignation(Long unitId, EffectiveDateDto effectiveDate);

    /**
     * Get all records on Unit Table
     * @return a list with all the Units
     */
    public List<Unit> getAllUnits();

}
