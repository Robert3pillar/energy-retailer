package com.vistra.energyretailer.energyretailerapi.dtos;

public class MarketDesignationDto {

    private String registrationCode;
    private Integer marketShare;
    private Long marketId;

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public Integer getMarketShare() {
        return marketShare;
    }

    public void setMarketShare(Integer marketShare) {
        this.marketShare = marketShare;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }
}
