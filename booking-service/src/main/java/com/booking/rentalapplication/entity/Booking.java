package com.booking.rentalapplication.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Pick up date is required")
    private LocalDate pickupDate;

    @Future(message = "Drop date must be in the future")
    private LocalDate dropDate;

    @Min(value = 1, message = "Number of adults must not be less that 1")
    private int numOfAdults;
    
    
    private boolean submitted;
    
    private long day;
    
    private float totalCost;

    private String bookingConfirmationCode;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
    private Long userId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "vehicle_id")
    private Long vehicleId;

//    public void calculateTotalNumberOfGuest() {
//        this.totalNumOfGuest = this.numOfAdults + this.numOfChildren;
//    }

//    public void setNumOfAdults(int numOfAdults) {
//        this.numOfAdults = numOfAdults;
//        calculateTotalNumberOfGuest();
//    }

//    public void setNumOfChildren(int numOfChildren) {
//        this.numOfChildren = numOfChildren;
//        calculateTotalNumberOfGuest();
//    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", pickupDate=" + pickupDate +
                ", dropDate=" + dropDate +
                ", numOfAdults=" + numOfAdults +
                ", submitted=" + submitted +
                ", day=" + day +
                ", totalCost=" + totalCost +
                ", bookingConfirmationCode='" + bookingConfirmationCode + '\'' +
                '}';
    }
}
