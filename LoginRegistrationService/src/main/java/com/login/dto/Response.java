package com.login.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.login.model.User;

import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int statusCode;
    private String message;
    private Long id;
    private String token;
    private String role;
    private String expirationTime;
    private String bookingConfirmationCode;
//    private boolean submitted;

    private User user;
//    private VehicleDTO vehicle;
//    private BookingDTO booking;
    private List<User> userList;
//    private List<VehicleDTO> vehicleList;
//    private List<BookingDTO> bookingList;


}
