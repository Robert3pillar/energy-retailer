package com.vistra.energyretailer.database.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "unit_market_designation")
public class UnitMarketDesignation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(targetEntity = Unit.class)
    @JoinColumn(name = "unit_id", referencedColumnName = "id", nullable = false)
    private Long unitId;

    @OneToOne(targetEntity = Market.class)
    @JoinColumn(name = "market_id", referencedColumnName = "id", nullable = false)
    private Long marketId;

    @Column(name = "effective_date", nullable = false)
    private Date effectiveDate;

    @Column(name = "registration_code", nullable = false)
    private String registrationCode;

    @Column(name = "market_share_percent", nullable = false)
    private Integer marketSharePercent;

    public UnitMarketDesignation() {
    }

    public UnitMarketDesignation(Long id, Long unitId, Long marketId, Date effectiveDate, String registrationCode, Integer marketSharePercent) {
        this.id = id;
        this.unitId = unitId;
        this.marketId = marketId;
        this.effectiveDate = effectiveDate;
        this.registrationCode = registrationCode;
        this.marketSharePercent = marketSharePercent;
    }

    public Long getId() {
        return id;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public Integer getMarketSharePercent() {
        return marketSharePercent;
    }

    public void setMarketSharePercent(Integer marketSharePercent) {
        this.marketSharePercent = marketSharePercent;
    }
}