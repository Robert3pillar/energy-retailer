package com.vistra.energyretailer.energyretailerapi.services;

import com.vistra.energyretailer.database.model.Unit;
import com.vistra.energyretailer.database.model.UnitMarketDesignation;
import com.vistra.energyretailer.database.repositories.UnitMarketDesignationRepository;
import com.vistra.energyretailer.database.repositories.UnitRepository;
import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDateDto;
import com.vistra.energyretailer.energyretailerapi.helper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EnergyRetailerServiceImpl implements EnergyRetailerService {

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private UnitMarketDesignationRepository unitMarketDesignationRepository;

    @Autowired
    private Converter converter;

    @Override
    public boolean saveUnitMarketDesignation(Long unitId, EffectiveDateDto effectiveDate) throws ParseException {
        List<UnitMarketDesignation> unitMarketDesignations;

        unitMarketDesignations = converter.effectiveDateDtoToUnitMarketDesignationConverter(unitId, effectiveDate);

        unitMarketDesignations = unitMarketDesignationRepository.saveAll(unitMarketDesignations);

        if (Objects.isNull(unitMarketDesignations)){
            return false;
        }

        return true;
    }

    @Override
    public Page<Unit> getAllUnits(PageRequest pageRequest) {
        return unitRepository.findAll(pageRequest);
    }
}
