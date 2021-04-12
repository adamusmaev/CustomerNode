package com.detailsrequestmodels;

import lombok.Data;

@Data
public class CustomerDetailsRequestModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}
