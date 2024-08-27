package com.login.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.login.dto.AuthDto;
import com.login.dto.EntityToDto;
import com.login.dto.Response;
import com.login.dto.UserDto;
import com.login.exception.InvalidCredentialException;
import com.login.exception.UserAlreadyPresentException;
import com.login.exception.UserNotFoundException;
import com.login.model.User;
import com.login.repository.UserRepository;
import com.login.security.Jwtutil;

@Service
public class UserServiceImpl implements UserService{

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	Jwtutil jwtutil;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Response  registerUser(User user) throws UserAlreadyPresentException {
		 Response response = new Response();
	        try {
	            if (user.getRole() == null || user.getRole().isBlank()) {
	                user.setRole("USER");
	            }
	            if (userRepository.existsByEmail(user.getEmail())) {
	                throw new UserAlreadyPresentException(user.getEmail() + "Already Exists");
	            }
	            user.setPassword(passwordEncoder.encode(user.getPassword()));
	            User savedUser = userRepository.save(user);
//	            UserDTO userDTO = Utils.mapUserEntityToUserDTO(savedUser);
	            response.setStatusCode(200);
	            response.setUser(savedUser);
	        } catch (UserAlreadyPresentException e) {
	            response.setStatusCode(400);
	            response.setMessage(e.getMessage());
	        } catch (Exception e) {
	            response.setStatusCode(500);
	            response.setMessage("Error Occurred During USer Registration " + e.getMessage());

	        }
	        return response;
	}

//	@Override
//	public String genrateUniqueRandomId() {
//		Random random = new Random();
//		String id = String.valueOf(random.nextInt(999999)+1);
//		
//		Optional<User> user = userRepository.findById(id);
//		if(user.isPresent()) {
//			id = genrateUniqueRandomId();
//		}
//		
//		return id;
//	}
//	 public Response login(LoginRequest loginRequest) {
//
//	        Response response = new Response();
//
//	        try {
//	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//	            Vehicle u = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new OurException("user Not found"));
//
//	            var token = jwtutil.generateToken(u.getName(),u.getRole(),u.getId(),u.getEmail());
//	            response.setStatusCode(200);
//	            response.setToken(token);
//	            response.setRole(user.getRole());
//	            response.setExpirationTime("7 Days");
//	            response.setMessage("successful");
//
//	        } catch (InvalidCredentialException e) {
//	            response.setStatusCode(404);
//	            response.setMessage(e.getMessage());
//
//	        } catch (Exception e) {
//
//	            response.setStatusCode(500);
//	            response.setMessage("Error Occurred During USer Login " + e.getMessage());
//	        }
//	        return response;
//	    }
	@Override
	public Response login(AuthDto loginUser) throws InvalidCredentialException {
		//need to apply jwt
		Response response=new Response();
 	   String str = null;
 	   try {
	 	   Authentication authentication =  authenticationManager.authenticate(
	 			   new UsernamePasswordAuthenticationToken(
	                        loginUser.getEmail(),
	                        loginUser.getPassword()
	                ));
	 	   if(authentication.isAuthenticated()) {
	 		   User u = userRepository.findByNameOrEmail(loginUser.getEmail(), loginUser.getEmail());
	 		   str = jwtutil.generateToken(u.getName(),u.getRole(),u.getId(),u.getEmail());
	 		  response.setStatusCode(200);
	            response.setToken(str);
	            response.setId(u.getId());
	            response.setRole(u.getRole());
	            response.setExpirationTime("7 Days");
	            response.setMessage("successful");
	 	   	   return response;
	 	   }else {
	 		  throw new InvalidCredentialException("Invalid Email or password");
	 	   }
 	   }
 	   catch(BadCredentialsException e) {
 		   throw new InvalidCredentialException("Invalid Email or password");
 	   }
	}
	
	 @Override
	    public Response deleteUser(String userId) {

	        Response response = new Response();

	        try {
	            userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
	            userRepository.deleteById(Long.valueOf(userId));
	            response.setStatusCode(200);
	            response.setMessage("successful");

	        } catch (UsernameNotFoundException e) {
	            response.setStatusCode(404);
	            response.setMessage(e.getMessage());

	        } catch (Exception e) {

	            response.setStatusCode(500);
	            response.setMessage("Error getting all users " + e.getMessage());
	        }
	        return response;
	    }
	 
	@Override
    public Response getMyInfo(String email) {

        User u =  userRepository.findByEmail(email);
        Response response=new Response();
//        System.out.println(u);
        if(u!=null){
        	response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(u);
        	return response;

//            return EntityToDto.convertToDto(u);
        }
        throw new UsernameNotFoundException("User with username not found");
    }

	
	@Override
    public Response getAllUsers() {

        Response response = new Response();
        try {
            List<User> userList = userRepository.findAll();
//            List<UserDTO> userDTOList = Utils.mapUserListEntityToUserListDTO(userList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUserList(userList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

	public Response getUserById(String userId) {

        Response response = new Response();

        try {
            User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
//            UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(user);

        } catch (UsernameNotFoundException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
	}
	

}
