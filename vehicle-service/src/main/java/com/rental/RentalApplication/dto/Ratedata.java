package com.rental.RentalApplication.dto;


import com.rental.RentalApplication.entity.Vehicle;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
//@Entity
//@Table(name = "ratedata")
@AllArgsConstructor
@NoArgsConstructor
public class Ratedata {
    
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    
    private int star;
    private String feedback;
    private Long userId;
    

//    @ManyToOne(fetch = FetchType.LAZY) // Adjust fetch type as needed
//    @JoinColumn(name = "vehicle_id")
//    private Vehicle vehicle;
//    // Constructors, getters, setters

@Override
public String toString() {
    return "Vehicle{" +
//            "id=" + id +
            ", star='" + star + '\'' +
            ", feedback='" + feedback + '\'' +
            ", userId='" + userId + '\'' +
            '}';
}
}

