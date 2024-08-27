package com.rental.RentalApplication.serviceimple;

import com.rental.RentalApplication.dto.Ratedata;
import com.rental.RentalApplication.dto.Response;
import com.rental.RentalApplication.dto.VehicleDTO;
import com.rental.RentalApplication.entity.Vehicle;
import com.rental.RentalApplication.exception.OurException;
import com.rental.RentalApplication.repo.VehicleRepository;
import com.rental.RentalApplication.service.AwsS3Service;
import com.rental.RentalApplication.serviceinterfac.IVehicleService;

import org.modelmapper.ModelMapper;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class VehicleService implements IVehicleService {


    @Autowired(required=true)
    private VehicleRepository vehicleRepository;
    @Autowired(required=true)
    ModelMapper modelMapper;
    @Autowired(required=true)
    private AwsS3Service awsS3Service;

    @Override
    public Response  addNewVehicle(MultipartFile photo, String vehicleType, BigDecimal vehiclePrice, String description, String vehicleName, int vehicleAvailable,String fuelType,  boolean ads, String brand) {
        Response response = new Response();

        try {
            String imageUrl = awsS3Service.saveImageToS3(photo);
            Vehicle vehicle = new Vehicle();
            vehicle.setVehiclePhotoUrl(imageUrl);
            vehicle.setVehicleType(vehicleType);
            vehicle.setVehiclePrice(vehiclePrice);
            vehicle.setVehicleDescription(description);
            vehicle.setVehicleName(vehicleName);
            vehicle.setFuelType(fuelType);
            vehicle.setVehicleAvailable(vehicleAvailable);
            vehicle.setAds(ads);
            vehicle.setBrand(brand);
//            vehicle.setRating(null);
            Vehicle savedVehicle = vehicleRepository.save(vehicle);
//            VehicleDTO vehicleDTO = Utils.mapVehicleEntityToVehicleDTO(savedVehicle);
//            VehicleDTO vehicleDTO=modelMapper.map(savedVehicle,VehicleDTO.class);
            response.setStatusCode(200);
            response.setMessage("successful");
//            response.setVehicle(vehicleDTO);
            response.setVehicle(vehicle);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a Vehicle " + e.getMessage());
        }
        return response;
    }

    @Override
    public List<String> getAllVehicleTypes() {
        return vehicleRepository.findDistinctVehicleTypes();
//        return null;
    }
    
    @Override
    public List<String> getAllFuelTypes() {
    	return vehicleRepository.findDistinctFuelTypes();
    	
    }
    
    @Override
    public List<String> getAllBrandTypes() {
    	return vehicleRepository.findDistinctBrandTypes();
    	
    }
    
    @Override
    public Response getAllVehicles() {
        Response response = new Response();

        try {
            List<Vehicle> vehicleList = vehicleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List<VehicleDTO> vehicleDTOList = Arrays.asList(modelMapper.map(vehicleList,VehicleDTO.class));
            response.setStatusCode(200);
            response.setMessage("successful");
           System.out.println(vehicleList);
           System.out.println(vehicleDTOList);
//            response.setMessage("successful");
           	response.setVehicleList(vehicleList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
//        return null;
    }
//
    @Override
    public Response deleteVehicle(Long vehicleId) {
        Response response = new Response();

        try {
        	vehicleRepository.findById(vehicleId).orElseThrow(() -> new OurException("bike Not Found"));
        	vehicleRepository.deleteById(vehicleId);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error deleting the bike " + e.getMessage());
        }
        return response;
//        return null;
    }
//
    @Override
    public Response updateVehicle(Long vehicleId, String vehicleDescription, String vehicleType, BigDecimal vehiclePrice, MultipartFile photo, String vehicleName, int vehicleAvailable,String fuelType,  boolean ads, String brand) {
        Response response = new Response();
        try {
            String imageUrl = null;
            if (photo != null && !photo.isEmpty()) {
                imageUrl = awsS3Service.saveImageToS3(photo);
            }
            
            Vehicle vehicle = vehicleRepository.findById(vehicleId)
                    .orElseThrow(() -> new OurException("Vehicle Not Found"));

            // Update fields only if they are not null
            if (vehicleType != null) {
                vehicle.setVehicleType(vehicleType);
            }
            if (vehiclePrice != null) {
                vehicle.setVehiclePrice(vehiclePrice);
            }
            if (vehicleDescription != null) {
                vehicle.setVehicleDescription(vehicleDescription);
            }
            if (vehicleName != null) {
                vehicle.setVehicleName(vehicleName);
            }
            if (fuelType != null) {
            	vehicle.setFuelType(fuelType);
            }
            if (fuelType != null) {
            	vehicle.setAds(ads);
            }
            if (brand != null) {
            	vehicle.setBrand(brand);
            }
            if (vehicleAvailable != -1) {
                vehicle.setVehicleAvailable(vehicleAvailable);
            }
            if (imageUrl != null) {
                vehicle.setVehiclePhotoUrl(imageUrl);
            }

            Vehicle updatedVehicle = vehicleRepository.save(vehicle);

            response.setStatusCode(200);
            response.setMessage("Update successful");
            response.setVehicle(updatedVehicle);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error updating the vehicle: " + e.getMessage());
            // Log the exception for debugging purposes
            e.printStackTrace();
        }

        return response;    
    }
    
    

    @Override
    public Response getVehicleById(Long vehicleId) {
        Response response = new Response();

        try {
            Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new OurException("Vehicle Not Found"));
//            VehicleDTO vehicleDTO = Utils.mapVehicleEntityToVehicleDTOPlusBookings(room);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setVehicle(vehicle);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting the bike " + e.getMessage());
        }
        return response;
    }
//
//    @Override
//    public Response getAvailableVehiclesByDataAndType(LocalDate pickUpDate, LocalDate dropDate, String vehicleType) {
//        Response response = new Response();
//
//        try {
//            List<Vehicle> availableVehicles = vehicleRepository.findAvailableVehiclesByDatesAndTypes(pickUpDate, dropDate, vehicleType);
////            List<VehicleDTO> roomDTOList = Utils.mapVehicleListEntityToVehicleListDTO(availableVehicles);
//            response.setStatusCode(200);
//            response.setMessage("successful");
//            response.setVehicleList(roomDTOList);
//
//        } catch (Exception e) {
//            response.setStatusCode(500);
//            response.setMessage("Error saving a vehicle " + e.getMessage());
//        }
//        return null;
//    }

    @Override
    public Response getAllAvailableVehicles() {
        Response response = new Response();

        try {
            List<Vehicle> vehicleList = vehicleRepository.getAllAvailableVehicles();
//            List<VehicleDTO> vehicleDTOList = Utils.mapVehicleListEntityToVehicleListDTO(roomList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setVehicleList(vehicleList);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a vehicle " + e.getMessage());
        }
        return response;
    }

	@Override
	public Response getAllVehiclesByAds() {
		Response response = new Response();

        try {
            List<Vehicle> vehicleList = vehicleRepository.getAllAvailableVehiclesByAds();
//            List<VehicleDTO> vehicleDTOList = Utils.mapVehicleListEntityToVehicleListDTO(roomList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setVehicleList(vehicleList);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a vehicle " + e.getMessage());
        }
        return response;
	}


//	@Override
//	public Response rateVehicle(Long vehicleId, Long userId, int star,String feedback) {
//		Response response=new Response();
//		
//		try{
//			Vehicle vehicle = vehicleRepository.findById(vehicleId)
//		
//                .orElseThrow(() -> new OurException("Vehicle Not Found"));
//			List<Ratedata> li=vehicle.getRating();
//			Ratedata rate=new Ratedata();
//			rate.setStar(star);
//			rate.setFeedback(feedback);
//			rate.setUserId(userId);
////			rate.setVehicle(vehicle);
//			System.err.println(rate);
//			li.add(rate);
//			System.err.println(li);
//			vehicle.setRating(li);
//			System.err.println(vehicle);
//			Vehicle updatedVehicle = vehicleRepository.save(vehicle);
//			System.out.println(updatedVehicle);
//			response.setStatusCode(200);
//			response.setMessage("Update successful");
//			response.setVehicle(updatedVehicle);
//
//		}catch (OurException e) {
//			response.setStatusCode(404);
//        	response.setMessage(e.getMessage());
//		} catch (Exception e) {
//			response.setStatusCode(500);
//			response.setMessage("Error updating the vehicle: " + e.getMessage());
//        // Log the exception for debugging purposes
//			e.printStackTrace();
//		}
//		return response;
//	}

}
