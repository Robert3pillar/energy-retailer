package com.vistra.energyretailer.energyretailerapi.validator;

import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDateDto;

import java.util.Map;

public interface EnergyRetailerValidator {
    Map<String,String> validatePostUnitMarketDesignationData(Long unitId,EffectiveDateDto effectiveDate);
}
