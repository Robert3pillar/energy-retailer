package com.vistra.energyretailer.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "unit_market_designation")
public class UnitMarketDesignation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(targetEntity = Unit.class)
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private Unit unit;

    @OneToOne(targetEntity = Market.class)
    @JoinColumn(name = "market_id", referencedColumnName = "id")
    private Market market;

    @Column(name = "effective_date", nullable = false)
    private Date effectiveDate;

    @Column(name = "registration_code", unique = true, nullable = false)
    private String registrationCode;

    @Column(name = "market_share_percent", nullable = false)
    private Integer marketSharePercent;

    public UnitMarketDesignation() {
    }

    public UnitMarketDesignation(Long id, Unit unit, Market market, Date effectiveDate, String registrationCode, Integer marketSharePercent) {
        this.id = id;
        this.unit = unit;
        this.market = market;
        this.effectiveDate = effectiveDate;
        this.registrationCode = registrationCode;
        this.marketSharePercent = marketSharePercent;
    }

    public Long getId() {
        return id;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
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