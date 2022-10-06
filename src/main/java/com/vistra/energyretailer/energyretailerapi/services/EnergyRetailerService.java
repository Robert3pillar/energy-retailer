package com.vistra.energyretailer.energyretailerapi.services;

import com.vistra.energyretailer.database.model.Unit;
import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.text.ParseException;

public interface EnergyRetailerService {

    /**
     * Saves a Market Designation
     *
     * @return true if transaction is successful
     */
    public boolean saveUnitMarketDesignation(Long unitId, EffectiveDateDto effectiveDate) throws ParseException;

    /**
     * Get all records on Unit Table
     *
     * @param pageRequest pagination and sorting
     * @return a list with all the Units
     */
    public Page<Unit> getAllUnits(PageRequest pageRequest);

}
