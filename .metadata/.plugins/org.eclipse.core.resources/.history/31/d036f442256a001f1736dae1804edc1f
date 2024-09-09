package com.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.login.dto.AuthDto;
import com.login.dto.Response;
import com.login.dto.UserDto;
import com.login.exception.InvalidCredentialException;
import com.login.exception.UserAlreadyPresentException;
import com.login.model.User;
import com.login.security.Jwtutil;
import com.login.service.CustomUserDetailsService;
import com.login.service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private Jwtutil jwtutil;
	
		
	@PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid User user) throws UserAlreadyPresentException {
          //  user.setId(userService.genrateUniqueRandomId());
           	Response response=(userService.registerUser(user)) ;
           return ResponseEntity.status(response.getStatusCode()).body(response);
//           			{
//            	
//            	if(user.getRole().equalsIgnoreCase("User"))
//            	{
//            		return new ResponseEntity<String>("Customer added successfully!!!",HttpStatus.OK);
//            	}
//            	return new ResponseEntity<String>("Admin added successfully!!!",HttpStatus.OK);
//            		
//            }else {
//            	return new ResponseEntity<String>("Something went worng!!!",HttpStatus.INTERNAL_SERVER_ERROR);
//            }
	}
        

//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody @Valid AuthDto loginUser) throws InvalidCredentialException {
////    	Response response=   userService.login(loginUser);
////    	return ResponseEntity.status(response.getStatusCode()).body(response);
//    	   return ResponseEntity.ok(userService.login(loginUser));
//    }

	@PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody @Valid AuthDto loginUser) throws InvalidCredentialException {
		Response response = userService.login(loginUser);
		return ResponseEntity.status(response.getStatusCode()).body(response);
    }
   
//	public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
//        Response response = userService.login(loginRequest);
    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam("token") String token) {
        jwtutil.validateToken(token);
        return ResponseEntity.ok("Token is valid");
        
    }
        
    @DeleteMapping("/delete/{userId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteUSer(@PathVariable("userId") String userId) {
        Response response = userService.deleteUser(userId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/all")
    public ResponseEntity<Response> getAllUsers() {
        Response response = userService.getAllUsers();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
//    @GetMapping("/a")
//    public ResponseEntity<Response> getA() {
//    	Response response = new Response();
//    	response.setMessage("Working");
//    	response.setStatusCode(200);
    	
//    	return ResponseEntity.status(response.getStatusCode()).body(response);
//    }
    @GetMapping("/get-by-id/{userId}")
    public ResponseEntity<Response> getUserById(@PathVariable("userId") String userId) {
        Response response = userService.getUserById(userId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    
    @GetMapping("/get-logged-in-profile-info")
    public ResponseEntity<Response> getLoggedInUserProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Response response = userService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/getUser/{userName}")
    public ResponseEntity<Response> getMyInfo(@PathVariable String userName){
    	
    	System.out.println("hello world");
    	
        //return new ResponseEntity<>(userService.getUser(userName),HttpStatus.OK);
    	return ResponseEntity.ok(userService.getMyInfo(userName));
    }
}
