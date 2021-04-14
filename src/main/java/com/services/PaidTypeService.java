package com.services;


import com.entities.PaidType;
import com.repository.PaidTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

@Service
@Log4j
@RequiredArgsConstructor
public class PaidTypeService {


    private final PaidTypeRepository paidTypeRepository;

    public PaidType findPaidTypeById(Integer paidTypeId) {
        PaidType paidType = paidTypeRepository.findById(paidTypeId).orElse(null);
        if (paidType == null) log.error(new IllegalArgumentException("PaidType not found"));
        return paidType;
    }

    public Iterable<PaidType> findAllPaidType() {
        return paidTypeRepository.findAll();
    }

    public void savePaidType(PaidType paidType) {
        paidTypeRepository.save(paidType);
    }

    public void deletePaidType(PaidType paidType) {
        paidTypeRepository.delete(paidType);
    }
}
