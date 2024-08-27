package com.booking.rentalapplication.serviceinterface;

import com.booking.rentalapplication.dto.Response;
import com.booking.rentalapplication.entity.Booking;

public interface IBookingService {
//
    Response saveBooking(Long vehicleId, Long userId, Booking bookingRequest);
//
    Response findBookingByConfirmationCode(String confirmationCode);
//
    Response getAllBookings();
//
    Response cancelBooking(Long bookingId);
//    
    Response vehicleSubmit(Long bookingId);
//
	Response getUserBookings(Long userId);
}
