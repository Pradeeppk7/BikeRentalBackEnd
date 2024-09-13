package com.kyc.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String role;
    private Boolean kycVerified;
    
//    private List<BookingDTO> bookings = new ArrayList<>();

}
