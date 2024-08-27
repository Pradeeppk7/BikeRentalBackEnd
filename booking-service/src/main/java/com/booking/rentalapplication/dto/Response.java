package com.booking.rentalapplication.dto;


import com.booking.rentalapplication.entity.Booking;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    private boolean submitted;

    private User user;
    private Vehicle vehicle;
    private Booking booking;
    private List<User> userList;
    private List<Vehicle> vehicleList;
    private List<Booking> bookingList;


}
