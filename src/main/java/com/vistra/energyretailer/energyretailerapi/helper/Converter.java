package com.vistra.energyretailer.energyretailerapi.helper;

import com.vistra.energyretailer.database.model.UnitMarketDesignation;
import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDateDto;

import java.text.ParseException;
import java.util.List;

public interface Converter {

    /**
     * @param unitId           id of the Unit
     * @param effectiveDateDto to be converted into UnitMarketDesignation
     * @return UnitMarketDesignation entity
     */
    public List<UnitMarketDesignation> effectiveDateDtoToUnitMarketDesignationConverter(Long unitId, EffectiveDateDto effectiveDateDto) throws ParseException;

}
