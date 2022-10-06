package com.vistra.energyretailer.energyretailerapi.controllers;

import com.vistra.energyretailer.database.model.Unit;
import com.vistra.energyretailer.energyretailerapi.dtos.EffectiveDateDto;
import com.vistra.energyretailer.energyretailerapi.services.EnergyRetailerService;
import com.vistra.energyretailer.energyretailerapi.validator.EnergyRetailerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class EnergyRetailerController {

    @Autowired
    EnergyRetailerService energyRetailerService;

    @Autowired
    EnergyRetailerValidator energyRetailerValidator;


    @PostMapping(path = "marketdesignations/{unitId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity AddUnitMarketDesignation(@PathVariable("unitId") Long unitId,
                                                   @RequestBody EffectiveDateDto effectiveDate){
        boolean result;
        Map<String,String> validation;

        validation = energyRetailerValidator.validatePostUnitMarketDesignationData(unitId, effectiveDate);

        if(!validation.isEmpty()){
            return ResponseEntity.badRequest().body(validation);
        }

        try {
            result = energyRetailerService.saveUnitMarketDesignation(unitId, effectiveDate);
        } catch (ParseException e) {
            Map<String, String> map = new HashMap<>();
            map.put("errorMessage", e.getMessage());

            return ResponseEntity.internalServerError().body(map);
        }

        if(!result){

        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "units", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Unit>> getAllUnits(@RequestParam Optional<Integer> page,
                                                  @RequestParam Optional<Integer> pageSize,
                                                  @RequestParam Optional<String> sortBy){
        return new ResponseEntity<>(energyRetailerService.getAllUnits(PageRequest.of(page.orElse(0), pageSize.orElse(10),
                Sort.Direction.ASC, sortBy.orElse("id"))),HttpStatus.OK);
    }
}
