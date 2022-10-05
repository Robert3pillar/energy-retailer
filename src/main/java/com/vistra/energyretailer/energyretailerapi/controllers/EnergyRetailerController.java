package com.vistra.energyretailer.energyretailerapi.controllers;

import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDateDto;
import com.vistra.energyretailer.energyretailerapi.services.EnergyRetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class EnergyRetailerController {

    @Autowired
    EnergyRetailerService energyRetailerService;

    @PostMapping(path = "marketdesignations/{unitId}",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity AddUnitMarketDesignation(@PathVariable("unitId") Long unitId,
                                                   @RequestBody EffectiveDateDto effectiveDate){

        energyRetailerService.saveUnitMarketDesignation(unitId, effectiveDate);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
