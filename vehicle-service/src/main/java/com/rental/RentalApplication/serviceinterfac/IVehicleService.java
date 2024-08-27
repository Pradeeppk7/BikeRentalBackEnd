package com.rental.RentalApplication.serviceinterfac;

import org.springframework.web.multipart.MultipartFile;

import com.rental.RentalApplication.dto.Ratedata;
import com.rental.RentalApplication.dto.Response;
import com.rental.RentalApplication.entity.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IVehicleService {

    Response addNewVehicle(MultipartFile photo, String vehicleType, BigDecimal vehiclePrice, String description, String vehicleName, int vehicleAvailable, String fuelType, boolean ads, String brand);



    List<String> getAllVehicleTypes();
    
    List<String> getAllFuelTypes();
    
	List<String> getAllBrandTypes();
//
    Response getAllVehicles();

    Response deleteVehicle(Long vehicleId);
//
    Response updateVehicle(Long vehicleId, String vehicleDescription, String vehicleType, BigDecimal vehiclePrice, MultipartFile photo, String vehicleName, int vehicleAvailable, String fuelType, boolean ads, String brand);
//
    Response getVehicleById(Long vehicleId);
//
//    Response getAvailableVehiclesByDataAndType(LocalDate pickupDate, LocalDate dropDate, String vehicleType);

    Response getAllAvailableVehicles();



	Response getAllVehiclesByAds();









//	Response rateVehicle(Long vehicleId, Long userId, int star,String feedback);



//	Response updateviaobject(Long vehicleId, Vehicle v);
}
