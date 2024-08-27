package com.rental.RentalApplication.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.rental.RentalApplication.dto.BookingDTO;
import com.rental.RentalApplication.dto.Ratedata;

import jakarta.persistence.Transient;
@Data
@Entity
@Table(name = "vehicle")
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String vehicleName;
    private String vehicleType;
    private BigDecimal vehiclePrice;
    private String vehiclePhotoUrl;
    private int vehicleAvailable;
    private String vehicleDescription;
    private String fuelType;
    private boolean ads;
    private String brand;
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle", orphanRemoval = true)
//    private List<Ratedata> rating=new ArrayList<>();
    
    
//    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @Transient
//    private List<BookingDTO> bookings;


    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", vehicleType='" + vehicleType + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", vehicleAvailable='" + vehicleAvailable + '\'' +
                ", vehiclePrice=" + vehiclePrice +
                ", vehiclePhotoUrl='" + vehiclePhotoUrl + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", ads='" + ads + '\'' +
                ", vehicleDescription='" + vehicleDescription + '\'' +              
                ", brand='" + brand + '\'' +              
                '}';
    }
}
