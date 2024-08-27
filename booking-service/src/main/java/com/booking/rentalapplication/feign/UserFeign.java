package com.booking.rentalapplication.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.booking.rentalapplication.dto.Response;

	@FeignClient(name="user-service", url = "http://localhost:9006/user")
	
	public interface UserFeign {




		
//		@PostMapping("/add/{bookingId}")
//		public List<Passenger> addPassengersByBookingId(@RequestBody List<Passenger> passengersList,@PathVariable int bookingId );
		
		@GetMapping("/get-by-id/{userId}")
		public Response getUserById(@PathVariable Long userId);
//	//	
//		@GetMapping("/getByPassengerId/{passengerId}")
//		public Passenger getPassengerByPassengerId(@PathVariable int passengerId);
	//	
//		@DeleteMapping("/delete/{passengerId}")
//		public Passenger deletePassenger(@PathVariable int passengerId);
		
}

