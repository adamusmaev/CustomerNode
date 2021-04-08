package com.transfers;

import com.entities.PaidType;
import lombok.Data;

@Data
public class PaidTypeTransfer {

    private String name;

    public PaidTypeTransfer(PaidType paidType)
    {
        this.name = paidType.getName();
    }

}
