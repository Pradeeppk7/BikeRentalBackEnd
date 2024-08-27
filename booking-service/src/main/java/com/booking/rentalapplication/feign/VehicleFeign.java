package com.booking.rentalapplication.feign;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PutMapping;

import com.booking.rentalapplication.dto.Response;
import com.booking.rentalapplication.dto.Vehicle;



@FeignClient(name="vehicle-service", url = "http://localhost:9003/vehicles")
	
public interface VehicleFeign {




	
	@PutMapping("/update/{vehicleId}")
	public ResponseEntity<Response> updateVehicle(@PathVariable Long vehicleId,
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "vehicleType", required = false) String vehicleType,
            @RequestParam(value = "vehiclePrice", required = false) BigDecimal vehiclePrice,
            @RequestParam(value = "vehicleDescription", required = false) String vehicleDescription,
            @RequestParam(value = "vehicleName", required = false) String vehicleName,
            @RequestParam(value = "vehicleAvailable", required = false) int vehicleAvailable);
	
	@GetMapping("/vehicle-by-id/{vehicleId}")
	public Response getVehicleById(@PathVariable Long vehicleId);
//	
//	@GetMapping("/getByPassengerId/{passengerId}")
//	public Passenger getPassengerByPassengerId(@PathVariable int passengerId);
//	
//	@DeleteMapping("/delete/{passengerId}")
//	public Passenger deletePassenger(@PathVariable int passengerId);
	
//	@PutMapping("/updateviaobject/{vehicleId}")
//	public Response updateVehicle(Long id, Vehicle v);
	
}