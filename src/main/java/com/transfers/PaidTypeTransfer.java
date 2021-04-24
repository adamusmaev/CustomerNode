package com.transfers;

import com.entities.PaidType;
import lombok.Data;

@Data
public class PaidTypeTransfer {

    private Integer id;
    private String name;

    public PaidTypeTransfer(PaidType paidType) {
        this.id = paidType.getId();
        this.name = paidType.getName();
    }

}
