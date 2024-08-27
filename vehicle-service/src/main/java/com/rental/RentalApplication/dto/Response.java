package com.rental.RentalApplication.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.rental.RentalApplication.entity.Vehicle;

import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int statusCode;
    private String message;

    private String token;
    private String role;
    private String expirationTime;
    private String bookingConfirmationCode;
//    private boolean submitted;

    private UserDTO user;
    private Vehicle vehicle;
    private BookingDTO booking;
    private List<UserDTO> userList;
    private List<Vehicle> vehicleList;
    private List<BookingDTO> bookingList;


}
