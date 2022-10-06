package com.vistra.energyretailer.energyretailerapi.validator;

import com.vistra.energyretailer.database.model.Market;
import com.vistra.energyretailer.database.model.Unit;
import com.vistra.energyretailer.database.model.UnitMarketDesignation;
import com.vistra.energyretailer.database.repositories.MarketRepository;
import com.vistra.energyretailer.database.repositories.UnitMarketDesignationRepository;
import com.vistra.energyretailer.database.repositories.UnitRepository;
import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDate;
import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDateDto;
import com.vistra.energyretailer.energyretailerapi.dtos.MarketDesignationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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

        validation = validateIfCommonDataIsNullOrEmpty(unitId, effectiveDate, validation);

        if (!validation.isEmpty()) {
            return validation;
        }

        validation = validateIfMarketDesignationsDataIsNullOrEmpty(effectiveDate, validation);

        if (!validation.isEmpty()) {
            return validation;
        }

        validation = validateIfUnitExists(unitId, validation);

        if (!validation.isEmpty()) {
            return validation;
        }

        validation = validateMarketDesignations(effectiveDate.getMarketDesignations(), validation);

        return validation;
    }

    /**
     * @param unitId        id of Unit entity
     * @param effectiveDate object that contains Market Designations
     * @param error         object to be returned empty or with error
     * @return error if exists
     */
    private Map<String, String> validateIfCommonDataIsNullOrEmpty(Long unitId, EffectiveDateDto effectiveDate,
                                                                  Map<String, String> error) {

        if (Objects.isNull(effectiveDate.getEffectiveDate())) {
            error.put("errorMessage", "effectiveDate cannot be empty");
            return error;
        }
        if (Objects.isNull(effectiveDate.getEffectiveDate().getDate())
                || effectiveDate.getEffectiveDate().getDate().isEmpty()) {
            error.put("errorMessage", "date value cannot be empty");
            return error;
        }
        if (Objects.isNull(effectiveDate.getEffectiveDate().getTime())
                || effectiveDate.getEffectiveDate().getTime().isEmpty()) {
            error.put("errorMessage", "time value cannot be empty");
            return error;
        }
        if (Objects.isNull(effectiveDate.getMarketDesignations())
                || effectiveDate.getMarketDesignations().isEmpty()) {
            error.put("errorMessage", "marketDesignations cannot be empty");
            return error;
        }

        return error;
    }

    /**
     * @param effectiveDate object that contains Market Designations
     * @param error         object to be returned empty or with error
     * @return error if exists
     */
    private Map<String, String> validateIfMarketDesignationsDataIsNullOrEmpty(EffectiveDateDto effectiveDate,
                                                                              Map<String, String> error) {
        List<MarketDesignationDto> marketDesignations = effectiveDate.getMarketDesignations();

        for (MarketDesignationDto marketDesignationDto : marketDesignations) {
            if (Objects.isNull(marketDesignationDto.getRegistrationCode())
                    || marketDesignationDto.getRegistrationCode().isEmpty()) {
                error.put("errorMessage", "registrationCode cannot be empty");
                return error;
            }
            if (Objects.isNull(marketDesignationDto.getMarketShare())) {
                error.put("errorMessage", "marketShare cannot be empty");
                return error;
            }
            if (Objects.isNull(marketDesignationDto.getMarketId())) {
                error.put("errorMessage", "marketId cannot be empty");
                return error;
            }
        }

        return error;
    }

    /**
     * @param unitId id of Unit entity
     * @param error  object to be returned empty or with error
     * @return error if exists
     */
    private Map<String, String> validateIfUnitExists(Long unitId, Map<String, String> error) {
        Optional<Unit> unit = unitRepository.findById(unitId);
        if (!unit.isPresent()) {
            error.put("errorMessage", "Unit not found");
            return error;
        }
        return error;
    }

    /**
     * @param effectiveDate object that contains the Date
     * @param error         object to be returned empty or with error
     * @return error if exists
     */
    private Map<String, String> validateDateFormat(EffectiveDate effectiveDate, Map<String, String> error) {
        try {
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(effectiveDate.getFullDate());
        } catch (ParseException e) {
            error.put("errorMessage", "Date or time format not valid");
            return error;
        }
        return error;
    }

    /**
     * @param marketDesignations list with market designations
     * @param error              object to be returned empty or with error
     * @return error if exists
     */
    private Map<String, String> validateMarketDesignations(List<MarketDesignationDto> marketDesignations,
                                                           Map<String, String> error) {
        Set<String> registrationCodes = new HashSet<>();
        Set<Long> marketIds = new HashSet<>();
        int marketShare = 0;

        for (MarketDesignationDto marketDesignationDto : marketDesignations) {
            registrationCodes.add(marketDesignationDto.getRegistrationCode());
            marketIds.add(marketDesignationDto.getMarketId());
            marketShare += marketDesignationDto.getMarketShare();
            error = validateIfRegistrationCodeExists(marketDesignationDto.getRegistrationCode(), error);
            if (!error.isEmpty()) {
                return error;
            }
            error = validateIfMarketExists(marketDesignationDto.getMarketId(), error);
            if (!error.isEmpty()) {
                return error;
            }
        }

        if (registrationCodes.size() < marketDesignations.size()) {
            error.put("errorMessage", "registrationCodes must be unique");
            return error;
        }

        if (marketIds.size() < marketDesignations.size()) {
            error.put("errorMessage", "marketIds must be unique");
            return error;
        }

        if (marketShare != MARKETSHAREFULL) {
            error.put("errorMessage", "marketShare values sum must be equal to 100");
            return error;
        }

        return error;
    }

    /**
     * @param registrationCode market designation registration code
     * @param error            object to be returned empty or with error
     * @return error if exists
     */
    private Map<String, String> validateIfRegistrationCodeExists(String registrationCode, Map<String, String> error) {
        Optional<UnitMarketDesignation> unitMarketDesignation =
                unitMarketDesignationRepository.findByRegistrationCode(registrationCode);

        if (unitMarketDesignation.isPresent()) {
            error.put("errorMessage", "registrationCodes already exists");
            return error;
        }

        return error;
    }

    /**
     * @param marketId id of Market entity
     * @param error    object to be returned empty or with error
     * @return error if exists
     */
    private Map<String, String> validateIfMarketExists(Long marketId, Map<String, String> error) {
        Optional<Market> market = marketRepository.findById(marketId);
        if (!market.isPresent()) {
            error.put("errorMessage", "Market not found");
            return error;
        }
        return error;
    }


}
