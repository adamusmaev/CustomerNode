package com.services;


import com.entities.PaidType;
import com.repo.PaidTypeRepo;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Log4j
public class PaidTypeService {

    @Autowired
    PaidTypeRepo paidTypeRepo;

    public PaidType findPaidTypeById(Integer paidTypeId) {
        PaidType paidType = paidTypeRepo.findById(paidTypeId).orElse(null);
        if (paidType == null) log.error(new IllegalArgumentException("PaidType not found"));
        return paidType;
    }

    public Iterable<PaidType> findAllPaidType() {
        return paidTypeRepo.findAll();
    }

    public void savePaidType(PaidType paidType) {
        paidTypeRepo.save(paidType);
    }

    public void deletePaidType(PaidType paidType) {
        paidTypeRepo.delete(paidType);
    }
}
