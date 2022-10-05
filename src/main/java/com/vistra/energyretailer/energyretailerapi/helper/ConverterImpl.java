package com.vistra.energyretailer.energyretailerapi.helper;

import com.vistra.energyretailer.database.model.UnitMarketDesignation;
import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDateDto;
import com.vistra.energyretailer.energyretailerapi.dtos.MarketDesignationDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConverterImpl implements Converter{


    @Override
    public List<UnitMarketDesignation> effectiveDateDtoToUnitMarketDesignationConverter(Long unitId, EffectiveDateDto effectiveDateDto) throws ParseException {
        List<UnitMarketDesignation> unitMarketDesignations = new ArrayList<>();
        List<MarketDesignationDto> marketDesignations = effectiveDateDto.getMarketDesignations();
        UnitMarketDesignation unitMarketDesignation = new UnitMarketDesignation();
        Date date = new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse(effectiveDateDto.getEffectiveDate().getDate());


        for (MarketDesignationDto marketDesignationDto : marketDesignations) {

            unitMarketDesignation.setUnitId(unitId);
            unitMarketDesignation.setMarketId(marketDesignationDto.getMarketId());
            unitMarketDesignation.setEffectiveDate(date);
            unitMarketDesignation.setMarketSharePercent(marketDesignationDto.getMarketShare());
            unitMarketDesignation.setRegistrationCode(marketDesignationDto.getRegistrationCode());

            unitMarketDesignations.add(unitMarketDesignation);

        }

        return unitMarketDesignations;
    }
}
