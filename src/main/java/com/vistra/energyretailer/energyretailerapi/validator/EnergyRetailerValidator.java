package com.vistra.energyretailer.energyretailerapi.validator;

import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDateDto;

import java.util.Map;

public interface EnergyRetailerValidator {
    /**
     * @param unitId        id of Unit entity
     * @param effectiveDate object that contains Market Designations
     * @return map with error or empty if all data is correct
     */
    Map<String, String> validatePostUnitMarketDesignationData(Long unitId, EffectiveDateDto effectiveDate);
}
