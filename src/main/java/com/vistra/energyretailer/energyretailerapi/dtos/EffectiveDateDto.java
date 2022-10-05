package com.vistra.energyretailer.energyretailerapi.dtos;

import java.util.List;

public class EffectiveDateDto {

    private EffectiveDate effectiveDate;
    private List<MarketDesignationDto> marketDesignations;

    public EffectiveDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(EffectiveDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public List<MarketDesignationDto> getMarketDesignations() {
        return marketDesignations;
    }

    public void setMarketDesignations(List<MarketDesignationDto> marketDesignations) {
        this.marketDesignations = marketDesignations;
    }
}
