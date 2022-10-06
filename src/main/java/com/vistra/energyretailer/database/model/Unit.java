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
@Table(name = "unit")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "internal_short_name", nullable = false)
    private String internalShortName;

    @Column(name = "internal_long_name", nullable = false)
    private String internalLongName;

    @Column(name = "unit_start_date", nullable = false)
    private Date unitStartDate;

    @Column(name = "unit_end_date")
    private Date unitEndDate;

    @OneToOne(targetEntity = UnitType.class)
    @JoinColumn(name = "unit_type_id", referencedColumnName = "id")
    private UnitType unitType;

    @Column(name = "draft")
    private Boolean draft;

    @Column(name = "unit_identifier")
    private String unitIdentifier;

    public Unit() {
    }

    public Unit(Long id, String name, String internalShortName, String internalLongName, Date unitStartDate, Date unitEndDate, UnitType unitType, Boolean draft, String unitIdentifier) {
        this.id = id;
        this.name = name;
        this.internalShortName = internalShortName;
        this.internalLongName = internalLongName;
        this.unitStartDate = unitStartDate;
        this.unitEndDate = unitEndDate;
        this.unitType = unitType;
        this.draft = draft;
        this.unitIdentifier = unitIdentifier;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInternalShortName() {
        return internalShortName;
    }

    public void setInternalShortName(String internalShortName) {
        this.internalShortName = internalShortName;
    }

    public String getInternalLongName() {
        return internalLongName;
    }

    public void setInternalLongName(String internalLongName) {
        this.internalLongName = internalLongName;
    }

    public Date getUnitStartDate() {
        return unitStartDate;
    }

    public void setUnitStartDate(Date unitStartDate) {
        this.unitStartDate = unitStartDate;
    }

    public Date getUnitEndDate() {
        return unitEndDate;
    }

    public void setUnitEndDate(Date unitEndDate) {
        this.unitEndDate = unitEndDate;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitTypeId) {
        this.unitType = unitTypeId;
    }

    public Boolean getDraft() {
        return draft;
    }

    public void setDraft(Boolean draft) {
        this.draft = draft;
    }

    public String getUnitIdentifier() {
        return unitIdentifier;
    }

    public void setUnitIdentifier(String unitIdentifier) {
        this.unitIdentifier = unitIdentifier;
    }
}