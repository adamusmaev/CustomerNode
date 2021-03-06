package com.controllers;

import com.detailsrequestmodels.PaidTypeDetailsRequestModel;
import com.entities.PaidType;
import com.services.PaidTypeService;
import com.transfers.PaidTypeTransfer;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/paidtypes",produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j
public class PaidTypeController {

    public final PaidTypeService paidTypeService;

    public PaidTypeController(PaidTypeService paidTypeService) {
        this.paidTypeService = paidTypeService;
    }

    @GetMapping("/{paidTypeId}")
    @ResponseBody
    public PaidTypeTransfer findPaidTypeById(@PathVariable Integer paidTypeId) {
        PaidType paidType = paidTypeService.findPaidTypeById(paidTypeId);
        PaidTypeTransfer paidTypeTransfer = new PaidTypeTransfer(paidType);
        return paidTypeTransfer;
    }

    @GetMapping
    @ResponseBody
    public List<PaidTypeTransfer> findAll() {
        List<PaidTypeTransfer> paidTypeTransfers = new ArrayList<>();
        for (PaidType p : paidTypeService.findAllPaidType()) {
            PaidTypeTransfer paidTypeTransfer = new PaidTypeTransfer(p);
            paidTypeTransfers.add(paidTypeTransfer);
        }
        return paidTypeTransfers;
    }

    @PostMapping
    public void addPaidType(@RequestBody PaidTypeDetailsRequestModel paidTypeDRM) {
        PaidType paidType = new PaidType();
        paidType.setName(paidTypeDRM.getName());
        paidTypeService.savePaidType(paidType);
        log.info("Add " + paidType.toString());
    }

    @DeleteMapping("/{paidTypeId}")
    public void deletePaidType(@PathVariable Integer paidTypeId) {
        PaidType paidType = paidTypeService.findPaidTypeById(paidTypeId);
        if (!paidType.getCustomers().isEmpty()) log.error("This paid type hase customer");
        else {
            paidTypeService.deletePaidType(paidType);
            log.info("Delete paid type");
        }
    }
    @PutMapping("/{paidTypeId}")
    public void updatePaidType(@PathVariable Integer paidTypeId, @RequestBody PaidTypeDetailsRequestModel paidTypeDRM) {
        PaidType paidType = paidTypeService.findPaidTypeById(paidTypeId);
        paidType.setName(paidTypeDRM.getName());
        paidTypeService.savePaidType(paidType);
        log.info("Change paid type");
    }
}
