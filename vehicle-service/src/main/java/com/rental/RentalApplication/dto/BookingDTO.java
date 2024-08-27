package com.rental.RentalApplication.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {

    private Long id;
    private LocalDate pickupDate;
    private LocalDate dropDate;
    private int numOfAdults;
    private float totalCost;
    private boolean submitted;
    private Long day;    
    private String bookingConfirmationCode;
    private UserDTO user;
    private VehicleDTO vehicle;
}
