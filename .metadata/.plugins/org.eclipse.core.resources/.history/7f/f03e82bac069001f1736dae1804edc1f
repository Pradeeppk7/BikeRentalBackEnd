package com.booking.rentalapplication.serviceimple;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.booking.rentalapplication.dto.Response;
import com.booking.rentalapplication.dto.User;
import com.booking.rentalapplication.dto.Vehicle;
//import com.booking.rentalapplication.dto.Vehicle;
import com.booking.rentalapplication.entity.Booking;
import com.booking.rentalapplication.exception.OurException;
import com.booking.rentalapplication.feign.UserFeign;
import com.booking.rentalapplication.feign.VehicleFeign;
import com.booking.rentalapplication.repo.BookingRepository;
import com.booking.rentalapplication.serviceinterface.IBookingService;
import com.booking.rentalapplication.utils.Utils;

import java.time.temporal.ChronoUnit;
//import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired 
    private VehicleFeign vehicleF;
    @Autowired 
    private UserFeign userF;
//    @Autowired
//    private IVehicleService vehicleService;
//    @Autowired
//    private VehicleRepository vehicleRepository;
//    @Autowired
//    private UserRepository userRepository;

//
    @Override
    public Response saveBooking(Long vehicleId, Long userId, Booking bookingRequest) {
//
        Response response = new Response();

        try {
            if (bookingRequest.getDropDate().isBefore(bookingRequest.getPickupDate())) {
                throw new IllegalArgumentException("Check in date must come after check out date");
            }
//            Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new OurException("Vehicle Not Found"));
            Vehicle v =vehicleF.getVehicleById(vehicleId).getVehicle();
            User u =userF.getUserById(userId).getUser();
//            System.out.println(u.getUser());
//            response.setMessage("The id is "+u);
//            response.setStatusCode(200);
            
            
//            User user = userRepository.findById(userId).orElseThrow(() -> new OurException("User Not Found"));
//            System.out.println("a");
            	List<Booking> existingBookings=bookingRepository.findByVehicleId(vehicleId);
            	System.out.println(existingBookings);
//            	System.out.println("b");
//            List<Booking> existingBookings = v.getBookings();
            if(v.getVehicleAvailable()!=0) {
            	            	
            }
            else if(!vehicleIsAvailable(bookingRequest, existingBookings)) {
                throw new OurException("Vehicle not Available for selected date range");
            }else {
            	throw new OurException("Vehicle not Available for selected date range");
            }

//            System.out.println(v.getVehicleAvailable());
            v.setVehicleAvailable(v.getVehicleAvailable()-1);
//            System.out.println(v.getVehicleAvailable());
////            vehicleRepository.save(vehicle);
            vehicleF.updateVehicle(v.getId(),null, null, null, null, null, v.getVehicleAvailable());
            bookingRequest.setVehicleId(v.getId());
            bookingRequest.setUserId(u.getId());
            bookingRequest.setSubmitted(false);
            String bookingConfirmationCode = Utils.generateRandomConfirmationCode(10);
            bookingRequest.setBookingConfirmationCode(bookingConfirmationCode);
            if(bookingRequest.getDropDate()!=null&&bookingRequest.getPickupDate()!=null) {
            	bookingRequest.setDay(ChronoUnit.DAYS.between(bookingRequest.getPickupDate(),bookingRequest.getDropDate()));
            	if(bookingRequest.getDay()==0) {
            		bookingRequest.setDay(1);
            	}
            	float cost=bookingRequest.getDay()*v.getVehiclePrice().floatValue();
            	bookingRequest.setTotalCost(cost);
            }

            
            bookingRepository.save(bookingRequest);
            response.setStatusCode(200);
            response.setBookingConfirmationCode(bookingConfirmationCode);
            response.setMessage("successful"+bookingRequest);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Saving a booking: " + e.getMessage());

        }
//        System.out.println("Towel");
//        return null;
        return response;
    }


    @Override
    public Response findBookingByConfirmationCode(String confirmationCode) {

        Response response = new Response();

        try {
            Booking booking = bookingRepository.findByBookingConfirmationCode(confirmationCode).orElseThrow(() -> new OurException("Booking Not Found"));
//            BookingDTO bookingDTO = Utils.mapBookingEntityToBookingDTOPlusBookedRooms(booking, true);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setBooking(booking);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Finding a booking: " + e.getMessage());

        }
        return response;
    }

    @Override
    public Response getAllBookings() {

        Response response = new Response();
        try {
            List<Booking> bookingList = bookingRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
//            List<BookingDTO> bookingDTOList = Utils.mapBookingListEntityToBookingListDTO(bookingList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setBookingList(bookingList);
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Getting all bookings: " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response cancelBooking(Long bookingId) {

        Response response = new Response();

        try {
            Booking b=bookingRepository.findById(bookingId).orElseThrow(() -> new OurException("Booking Does Not Exist"));
            bookingRepository.deleteById(bookingId);
            long vehicleId=b.getVehicleId();
            Vehicle v=vehicleF.getVehicleById(vehicleId).getVehicle();
            v.setVehicleAvailable(v.getVehicleAvailable()+1);    
            vehicleF.updateVehicle(v.getId(),null, null, null, null, null, v.getVehicleAvailable());
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Cancelling a booking: " + e.getMessage());

        }
        return response;
    }

    


	@Override
	public Response vehicleSubmit(Long bookingId) {
		Response response = new Response();

        try {
            Booking b=bookingRepository.findById(bookingId).orElseThrow(() -> new OurException("Booking Does Not Exist"));
            long vehicleId=b.getVehicleId();
            Vehicle v=vehicleF.getVehicleById(vehicleId).getVehicle();
            if (b.isSubmitted()) {
                // Throw an exception if b is submitted
                throw new OurException("bike is already submitted.");
            }	
            b.setSubmitted(true);
            bookingRepository.save(b);
            v.setVehicleAvailable(v.getVehicleAvailable()+1);
            vehicleF.updateVehicle(v.getId(),null, null, null, null, null, v.getVehicleAvailable());
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error submitting  the vehicle: " + e.getMessage());

        }
        return response;
		
	}
    private boolean vehicleIsAvailable(Booking bookingRequest, List<Booking> existingBookings) {

		return existingBookings.stream()       		
        .noneMatch(existingBooking ->
                bookingRequest.getPickupDate().equals(existingBooking.getPickupDate())
                        || bookingRequest.getDropDate().isBefore(existingBooking.getDropDate())
                        || (bookingRequest.getPickupDate().isAfter(existingBooking.getPickupDate())
                        && bookingRequest.getPickupDate().isBefore(existingBooking.getDropDate()))
                        || (bookingRequest.getPickupDate().isBefore(existingBooking.getPickupDate())

                        && bookingRequest.getDropDate().equals(existingBooking.getDropDate()))
                        || (bookingRequest.getPickupDate().isBefore(existingBooking.getPickupDate())

                        && bookingRequest.getDropDate().isAfter(existingBooking.getDropDate()))

                        || (bookingRequest.getPickupDate().equals(existingBooking.getDropDate())
                        && bookingRequest.getDropDate().equals(existingBooking.getPickupDate()))

                        || (bookingRequest.getPickupDate().equals(existingBooking.getDropDate())
                        && bookingRequest.getDropDate().equals(bookingRequest.getPickupDate()))
        );
//		boolean op2= existingBookings.stream().noneMatch(item->item.getVehicle().getVehicleAvailable()>0);
//		if(op1 ||op2) {
//			return true;
//		}
//		return (option1 ||option2);
//		return false;
}


	@Override
	public Response getUserBookings(Long userId) {
		Response r=new Response();
		try {		
		List<Booking> booked=bookingRepository.findByUserId(userId);
		r.setBookingList(booked);
		r.setStatusCode(200);
        r.setMessage("successful");
		}
		 catch (OurException e) {
	            r.setStatusCode(404);
	            r.setMessage(e.getMessage());

	        } catch (Exception e) {
	            r.setStatusCode(500);
	            r.setMessage("Error submitting  the vehicle: " + e.getMessage());

	        }
		return r;
	}
}
