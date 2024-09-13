package com.rental.RentalApplication.controller;


import com.rental.RentalApplication.dto.Ratedata;
import com.rental.RentalApplication.dto.Response;
import com.rental.RentalApplication.entity.Vehicle;
import com.rental.RentalApplication.serviceinterfac.IVehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
//@CrossOrigin("*")
public class vehicleController {

    @Autowired 
    private IVehicleService vehicleService;
//    @Autowired
//    private IBookingService iBookingService;


    @PostMapping("/add")
    public ResponseEntity<Response> addNewVehicle(
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "vehicleType", required = false) String vehicleType,
            @RequestParam(value = "vehicleName", required = false) String vehicleName,
            @RequestParam(value = "vehicleAvailable", required = false) int vehicleAvailable,
            @RequestParam(value = "vehiclePrice", required = false) BigDecimal vehiclePrice,
            @RequestParam(value = "vehicleDescription", required = false) String vehicleDescription,
            @RequestParam(value = "fuelType", required = false) String fuelType,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "ads", required = false) boolean ads
    ) {

        if (photo == null || photo.isEmpty() || vehicleType == null || vehicleType.isBlank() || vehicleAvailable == 0 || vehicleName == null || vehicleName.isBlank() || vehiclePrice == null || vehicleType.isBlank()|| fuelType==null ||fuelType.isBlank() ||brand==null ||brand.isBlank()) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please provide values for all fields(photo, vehicleName, vehicleAvailable, vehicleType,vehiclePrice, fuelType)");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
       
        Response response = vehicleService.addNewVehicle(photo, vehicleType, vehiclePrice, vehicleDescription, vehicleName,vehicleAvailable,fuelType,ads,brand);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    
    @GetMapping("/all")
    public ResponseEntity<Response> getAllVehicles() {
        Response response = vehicleService.getAllVehicles();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/all/ads")
    public ResponseEntity<Response> getAllVehiclesByAds() {
    	Response response = vehicleService.getAllVehiclesByAds();
    	return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/types")
    public List<String> getVehicleTypes() {
        return vehicleService.getAllVehicleTypes();
    }
    
    @GetMapping("/fueltypes")
    public List<String> getFuelTypes() {
    	return vehicleService.getAllFuelTypes();
    }
    
    @GetMapping("/brandtypes")
    public List<String> getBrandTypes() {
    	return vehicleService.getAllBrandTypes();
    }

    @GetMapping("/vehicle-by-id/{vehicleId}")
    public ResponseEntity<Response> getVehicleById(@PathVariable Long vehicleId) {
        Response response = vehicleService.getVehicleById(vehicleId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    

    @GetMapping("/all-available-vehicles")
    public ResponseEntity<Response> getAvailableVehicles() {
        Response response = vehicleService.getAllAvailableVehicles();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
//
//    @GetMapping("/available-vehicle-by-date-and-type")
//    public ResponseEntity<Response> getAvailableVehiclesByDateAndType(
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate pickupDate,
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dropDate,
//            @RequestParam(required = false) String vehicleType
//    ) {
//        if (pickupDate == null || vehicleType == null || vehicleType.isBlank() || dropDate == null) {
//            Response response = new Response();
//            response.setStatusCode(400);
//            response.setMessage("Please provide values for all fields(checkInDate, roomType,checkOutDate)");
//            return ResponseEntity.status(response.getStatusCode()).body(response);
//        }
//        Response response = vehicleService.getAvailableVehiclesByDataAndType(pickupDate, dropDate, vehicleType);
//        return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
    
//
    @PutMapping("/update/{vehicleId}")
    public ResponseEntity<Response> updateVehicle(@PathVariable Long vehicleId,
                                               @RequestParam(value = "photo", required = false) MultipartFile photo,
                                               @RequestParam(value = "vehicleType", required = false) String vehicleType,
                                               @RequestParam(value = "vehiclePrice", required = false) BigDecimal vehiclePrice,
                                               @RequestParam(value = "vehicleDescription", required = false) String vehicleDescription,
                                               @RequestParam(value = "vehicleName", required = false) String vehicleName,
                                               @RequestParam(value = "vehicleAvailable", required = false) int vehicleAvailable,
                                               @RequestParam(value = "fuelType", required = false) String fuelType,
                                               @RequestParam(value = "brand", required = false) String brand,
                                               @RequestParam(value = "ads", required = false) boolean ads

    ) {
        Response response = vehicleService.updateVehicle(vehicleId, vehicleDescription, vehicleType, vehiclePrice, photo, vehicleName, vehicleAvailable,fuelType,ads,brand);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
//    @PutMapping("/rate/{vehicleId}/{userId}")
//    public ResponseEntity<Response> rateVehicle(@PathVariable Long vehicleId,
//    		@PathVariable Long userId,
//    		@RequestParam(value = "star", required = false) int star,
//    		@RequestParam(value = "feedback", required = false) String feedback
//    		) {
//    	Response response = vehicleService.rateVehicle(vehicleId, userId, star, feedback);
//    	return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
    
//    @PutMapping("/updateviaobject/{vehicleId}")
//    public ResponseEntity<Response> updateviaobject(@PathVariable Long vehicleId, 
//    		@RequestBody Vehicle v
//    		) {
//    	Response response = vehicleService.updateviaobject(vehicleId, v);
//    	return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
//
    @DeleteMapping("/delete/{vehicleId}")
    public ResponseEntity<Response> deleteVehicle(@PathVariable Long vehicleId) {
        Response response = vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }


}
