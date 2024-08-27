package com.rental.RentalApplication.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.rental.RentalApplication.entity.Vehicle;

import java.time.LocalDate;
import java.util.List;
 
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
 
    // Get all available vehicles
//    List<Vehicle> findByBookingsIsNull();
//    // Find available vehicles by dates and types
//    List<Vehicle> findByVehicleTypeContainingAndBookingsPickupDateAfterOrBookingsDropDateBefore(
//            String vehicleType, LocalDate dropDate, LocalDate pickupDate);
//    // Find distinct vehicle types
//    List<String> findDistinctVehicleType();
//}

//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.rental.RentalApplication.entity.Vehicle;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Repository
//public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
//
    @Query("SELECT DISTINCT r.vehicleType FROM Vehicle r")
    List<String> findDistinctVehicleTypes();

    @Query("SELECT DISTINCT r.fuelType FROM Vehicle r")
	List<String> findDistinctFuelTypes();

    @Query("SELECT DISTINCT r.brand FROM Vehicle r")
    List<String> findDistinctBrandTypes();


//    @Query("SELECT r FROM Vehicle r WHERE r.vehicleType LIKE %:vehicleType% AND r.id NOT IN (SELECT bk.vehicle.id FROM Booking bk WHERE" +
//            "(bk.pickupDate <= :dropDate) AND (bk.dropDate >= :pickupDate))")
//    List<Vehicle> findAvailableVehiclesByDatesAndTypes(LocalDate pickupDate, LocalDate dropDate, String vehicleType);


    @Query("SELECT r FROM Vehicle r WHERE r.vehicleAvailable !=0")
    List<Vehicle> getAllAvailableVehicles();
//    @Query("SELECT v FROM Vehicle v " +
//            "WHERE v.id NOT IN (" +
//            "    SELECT b.vehicle.id FROM Booking b" +
//            ")")
//     List<Vehicle> getAllAvailableVehicles();
//    
//    @Query("SELECT v FROM Vehicle v " +
//            "WHERE v.vehicleType LIKE %:vehicleType% " +
//            "AND v.id NOT IN (" +
//            "    SELECT b.vehicle.id FROM Booking b " +
//            "    WHERE (b.pickupDate <= :dropDate) AND (b.dropDate >= :pickupDate)" +
//            ")")
//     List<Vehicle> findAvailableVehiclesByDatesAndTypes(
//             @Param("pickupDate") LocalDate pickupDate,
//             @Param("dropDate") LocalDate dropDate,
//             @Param("vehicleType") String vehicleType
//     );
//    
//    @Query("SELECT DISTINCT v.vehicleType FROM Vehicle v")
//    List<String> findDistinctVehicleTypes();

    @Query("SELECT r FROM Vehicle r WHERE r.ads=true")
	List<Vehicle> getAllAvailableVehiclesByAds();

	




//  @Query("SELECT v FROM Vehicle v " +
//          "WHERE v.vehicleType LIKE %:vehicleType% " +
//          "AND v.id NOT IN (" +
//          "    SELECT b.vehicle.id FROM Booking b " +
//          "    WHERE (b.pickupDate <= :dropDate) AND (b.dropDate >= :pickupDate)" +
//          ")")
//	List<Vehicle> findAvailableVehiclesByDatesAndTypes(LocalDate pickUpDate, LocalDate dropDate, String vehicleType);
}
