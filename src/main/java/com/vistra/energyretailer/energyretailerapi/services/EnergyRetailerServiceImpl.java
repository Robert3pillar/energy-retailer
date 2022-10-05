package com.vistra.energyretailer.energyretailerapi.services;

import com.vistra.energyretailer.database.model.Unit;
import com.vistra.energyretailer.database.repositories.MarketRepository;
import com.vistra.energyretailer.database.repositories.UnitMarketDesignationRepository;
import com.vistra.energyretailer.database.repositories.UnitRepository;
import com.vistra.energyretailer.database.repositories.UnitTypeRepository;
import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EnergyRetailerServiceImpl implements EnergyRetailerService {

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private UnitTypeRepository unitTypeRepository;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private UnitMarketDesignationRepository unitMarketDesignationRepository;

    @Override
    public boolean saveUnitMarketDesignation(Long unitId, EffectiveDateDto effectiveDate) {
        return false;
    }

    @Override
    public List<Unit> getAllUnits() {
        return null;
    }
}
