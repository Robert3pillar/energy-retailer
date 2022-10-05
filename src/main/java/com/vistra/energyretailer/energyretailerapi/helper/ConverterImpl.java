package com.vistra.energyretailer.energyretailerapi.helper;

import com.vistra.energyretailer.database.model.UnitMarketDesignation;
import com.vistra.energyretailer.database.repositories.MarketRepository;
import com.vistra.energyretailer.database.repositories.UnitRepository;
import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDateDto;
import com.vistra.energyretailer.energyretailerapi.dtos.MarketDesignationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ConverterImpl implements Converter{
    @Autowired
    UnitRepository unitRepository;

    @Autowired
    MarketRepository marketRepository;

    @Override
    public List<UnitMarketDesignation> effectiveDateDtoToUnitMarketDesignationConverter(Long unitId, EffectiveDateDto effectiveDateDto) throws ParseException {
        List<UnitMarketDesignation> unitMarketDesignations = new ArrayList<>();
        List<MarketDesignationDto> marketDesignations = effectiveDateDto.getMarketDesignations();
        UnitMarketDesignation unitMarketDesignation = new UnitMarketDesignation();
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(effectiveDateDto.getEffectiveDate().getDate());


        for (MarketDesignationDto marketDesignationDto : marketDesignations) {

            unitMarketDesignation.setUnit(unitRepository.findById(unitId).get());
            unitMarketDesignation.setMarket(marketRepository.findById(marketDesignationDto.getMarketId()).get());
            unitMarketDesignation.setEffectiveDate(date);
            unitMarketDesignation.setMarketSharePercent(marketDesignationDto.getMarketShare());
            unitMarketDesignation.setRegistrationCode(marketDesignationDto.getRegistrationCode());

            unitMarketDesignations.add(unitMarketDesignation);

        }

        return unitMarketDesignations;
    }
}
