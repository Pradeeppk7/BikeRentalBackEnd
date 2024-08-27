package com.booking.rentalapplication.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.booking.rentalapplication.dto.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.booking.rentalapplication.entity.Booking;
import com.booking.rentalapplication.serviceinterface.IBookingService;

@RestController
@RequestMapping("/bookings")
//@CrossOrigin("*")
public class BookingController {
    @Autowired
    private IBookingService bookingService;    
    @PostMapping("/book-vehicle/{vehicleId}/{userId}")
//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Response> saveBookings(@PathVariable Long vehicleId,
                                                 @PathVariable Long userId,
                                                 @RequestBody Booking bookingRequest) {
        Response response = bookingService.saveBooking(vehicleId, userId, bookingRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/all")
    public ResponseEntity<Response> getAllBookings() {
        Response response = bookingService.getAllBookings();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/booking-by-userid/{userId}")
    public ResponseEntity<Response> getUserBookings(@PathVariable Long userId) {
    	Response response = bookingService.getUserBookings(userId);
    	return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @PutMapping("/vehiclesubmit/{bookingId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> vehicleSubmit(@PathVariable Long bookingId) {
        Response response = bookingService.vehicleSubmit(bookingId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/get-by-confirmation-code/{confirmationCode}")
    public ResponseEntity<Response> getBookingByConfirmationCode(@PathVariable String confirmationCode) {
        Response response = bookingService.findBookingByConfirmationCode(confirmationCode);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @DeleteMapping("/cancel/{bookingId}")
//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Response> cancelBooking(@PathVariable Long bookingId) {
        Response response = bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


}
