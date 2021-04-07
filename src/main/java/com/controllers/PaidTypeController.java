package com.controllers;

import com.entities.Address;
import com.entities.PaidType;
import com.repo.PaidTypeRepo;
import com.services.PaidTypeService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j
public class PaidTypeController {

    public final PaidTypeService paidTypeService;

    public PaidTypeController(PaidTypeService paidTypeService) {
        this.paidTypeService = paidTypeService;
    }

    @GetMapping("/findPaidTypeById/")
    public PaidType findPaidTypeById(@RequestBody Integer paidTypeId) {
        PaidType paidType = paidTypeService.findPaidTypeById(paidTypeId);
        return paidTypeService.findPaidTypeById(paidTypeId);
    }

    @GetMapping("/findAllPaidType")
    public Iterable<PaidType> findAll()
    {
        return paidTypeService.findAllPaidType();
    }

    @PostMapping("/addPaidType/{paidTypeName}")
    public void addPaidType(@PathVariable String paidTypeName) {
        PaidType paidType = new PaidType();
        if (paidTypeName == null) log.error(new IllegalArgumentException("name is null"));
        paidType.setName(paidTypeName);
        paidTypeService.savePaidType(paidType);
    }

    @DeleteMapping("/deletePaidType/")
    public void deletePaidType(@RequestBody PaidType paidType) {
        /*PaidType paidType = findPaidTypeById(paidTypeId);
        if (paidType == null) {
            log.error(new IllegalArgumentException("PaidType not found"));
            return;
        }
        if (paidType.getCustomers().isEmpty()) {
            paidTypeService.deletePaidType(paidType);
        }
        else log.error(new IllegalArgumentException("Delete error"));*/
        paidTypeService.deletePaidType(paidType);
        log.info("@RequestBody");
    }


    @PutMapping("/updatePaidType/{paidTypeId:\\d+}/{paidTypeName}")
    public void updatePaidType(@PathVariable Integer paidTypeId, @PathVariable String paidTypeName)
    {
        PaidType paidType = findPaidTypeById(paidTypeId);
        paidType.setName(paidTypeName);
        paidTypeService.savePaidType(paidType);
    }
}
