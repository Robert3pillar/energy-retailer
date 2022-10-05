package com.vistra.energyretailer.energyretailerapi.validator;

import com.vistra.energyretailer.database.model.Market;
import com.vistra.energyretailer.database.model.Unit;
import com.vistra.energyretailer.database.model.UnitMarketDesignation;
import com.vistra.energyretailer.database.repositories.MarketRepository;
import com.vistra.energyretailer.database.repositories.UnitMarketDesignationRepository;
import com.vistra.energyretailer.database.repositories.UnitRepository;
import com.vistra.energyretailer.database.repositories.UnitTypeRepository;
import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDate;
import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDateDto;
import com.vistra.energyretailer.energyretailerapi.dtos.MarketDesignationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class EnergyRetailerValidatorImpl implements EnergyRetailerValidator {

    private static final int MARKETSHAREFULL = 100;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private UnitMarketDesignationRepository unitMarketDesignationRepository;

    @Autowired
    private MarketRepository marketRepository;

    @Override
    public Map<String, String> validatePostUnitMarketDesignationData(Long unitId, EffectiveDateDto effectiveDate) {
        Map<String, String> validation = new HashMap<>();

        validation = validateIfCommonDataIsEmpty(unitId, effectiveDate, validation);

        if (!validation.isEmpty()) {
            return validation;
        }

        validation = validateIfMarketDesignationsAreEmpty(effectiveDate, validation);

        if (!validation.isEmpty()) {
            return validation;
        }

        validation = validateUnit(unitId, validation);

        if (!validation.isEmpty()) {
            return validation;
        }

        validation = validateMarketDesignations(effectiveDate.getMarketDesignations(), validation);

        if (!validation.isEmpty()) {
            return validation;
        }

        return validation;
    }

    private Map<String, String> validateIfCommonDataIsEmpty(Long unitId, EffectiveDateDto effectiveDate, Map<String, String> errors) {

        if (Objects.isNull(unitId)) {
            errors.put("errorMessage", "UnitId value cannot be empty");
            return errors;
        }
        if (Objects.isNull(effectiveDate.getEffectiveDate().getDate())) {
            errors.put("errorMessage", "effectiveDate cannot be empty");
            return errors;
        }
        if (Objects.isNull(effectiveDate.getEffectiveDate())) {
            errors.put("errorMessage", "date value cannot be empty");
            return errors;
        }
        if (Objects.isNull(effectiveDate.getEffectiveDate().getTime())) {
            errors.put("errorMessage", "time value cannot be empty");
            return errors;
        }
        if (Objects.isNull(effectiveDate.getMarketDesignations())) {
            errors.put("errorMessage", "marketDesignations cannot be empty");
            return errors;
        }
        if (Objects.isNull(effectiveDate.getMarketDesignations())) {
            errors.put("errorMessage", "marketDesignations cannot be empty");
            return errors;
        }

        return errors;
    }

    private Map<String, String> validateIfMarketDesignationsAreEmpty(EffectiveDateDto effectiveDate, Map<String, String> errors) {
        List<MarketDesignationDto> marketDesignations = effectiveDate.getMarketDesignations();

        for (MarketDesignationDto marketDesignationDto : marketDesignations) {
            if (Objects.isNull(marketDesignationDto.getRegistrationCode())) {
                errors.put("errorMessage", "registrationCode cannot be empty");
                return errors;
            }
            if (Objects.isNull(marketDesignationDto.getMarketShare())) {
                errors.put("errorMessage", "marketShare cannot be empty");
                return errors;
            }
            if (Objects.isNull(marketDesignationDto.getMarketId())) {
                errors.put("errorMessage", "marketId cannot be empty");
                return errors;
            }
        }

        return errors;
    }

    private Map<String, String> validateUnit(Long unitId, Map<String, String> errors) {
        Optional<Unit> unit = unitRepository.findById(unitId);
        if (!unit.isPresent()) {
            errors.put("errorMessage", "Unit not found");
            return errors;
        }
        return errors;
    }

    private Map<String, String> validateDate(EffectiveDate effectiveDate, Map<String, String> errors) {
        try {
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(effectiveDate.getFullDate());
        } catch (ParseException e) {
            errors.put("errorMessage", "Date or time format not valid");
            return errors;
        }
        return errors;
    }

    private Map<String, String> validateMarketDesignations(List<MarketDesignationDto> marketDesignations,
                                                         Map<String, String> errors) {
        Set<String> registrationCodes = new HashSet<>();
        Set<Long> marketIds = new HashSet<>();
        int marketShare = 0;

        for (MarketDesignationDto marketDesignationDto : marketDesignations){
            registrationCodes.add(marketDesignationDto.getRegistrationCode());
            marketIds.add(marketDesignationDto.getMarketId());
            marketShare += marketDesignationDto.getMarketShare();
            errors = validateRegistrationCode(marketDesignationDto.getRegistrationCode(), errors);
            if (!errors.isEmpty()) {
                return errors;
            }
            errors = validateMarket(marketDesignationDto.getMarketId(), errors);
            if (!errors.isEmpty()) {
                return errors;
            }
        }

        if(registrationCodes.size() < marketDesignations.size()){
            errors.put("errorMessage", "registrationCodes must be unique");
            return errors;
        }

        if(marketIds.size() < marketDesignations.size()){
            errors.put("errorMessage", "marketIds must be unique");
            return errors;
        }

        if(marketShare != MARKETSHAREFULL){
            errors.put("errorMessage", "marketShare values sum must be equal to 100");
            return errors;
        }

        return errors;
    }

    private Map<String, String> validateRegistrationCode(String registrationCode, Map<String, String> errors) {
        Optional<UnitMarketDesignation> unitMarketDesignation =
                unitMarketDesignationRepository.findByRegistrationCode(registrationCode);

        if (unitMarketDesignation.isPresent()) {
            errors.put("errorMessage", "registrationCodes already exists");
            return errors;
        }

        return errors;
    }

    private Map<String, String> validateMarket(Long marketId, Map<String, String> errors) {
        Optional<Market> market = marketRepository.findById(marketId);
        if (!market.isPresent()) {
            errors.put("errorMessage", "Market not found");
            return errors;
        }
        return errors;
    }


}
