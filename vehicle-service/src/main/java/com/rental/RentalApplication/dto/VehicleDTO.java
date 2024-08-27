package com.rental.RentalApplication.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDTO {

    private Long id;
    private String vehicleName;
    private String vehicleType;
    private BigDecimal vehiclePrice;
    private String vehiclePhotoUrl;
    private String vehicleDescription;
    private int vehicleAvailable;
    private List<BookingDTO> bookings;
}
