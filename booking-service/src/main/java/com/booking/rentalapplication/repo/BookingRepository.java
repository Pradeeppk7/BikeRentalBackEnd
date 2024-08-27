package com.booking.rentalapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.rentalapplication.entity.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);

	List<Booking> findByVehicleId(Long vehicleId);

	List<Booking> findByUserId(Long userId);
}
