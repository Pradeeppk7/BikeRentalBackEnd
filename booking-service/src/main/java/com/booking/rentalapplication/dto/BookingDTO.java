package com.booking.rentalapplication.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
//import com.rental.RentalApplication.dto.UserDTO;

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
    private User user;
    private Vehicle vehicle;
}
