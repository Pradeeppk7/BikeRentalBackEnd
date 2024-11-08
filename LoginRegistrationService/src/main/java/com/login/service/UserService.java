package com.login.service;

import com.login.dto.AuthDto;
import com.login.dto.Response;
import com.login.dto.UserDto;
import com.login.exception.InvalidCredentialException;
import com.login.exception.UserAlreadyPresentException;
import com.login.model.User;

public interface UserService {

	public Response registerUser(User user) throws UserAlreadyPresentException;
	
	public Response getAllUsers();
//	public String genrateUniqueRandomId();
	public Response deleteUser(String userId);
	
	public Response login(AuthDto loginUser) throws InvalidCredentialException;

	public Response getMyInfo(String email);
	
    public Response getUserById(String userId);

//	public Response emailVerify(User user);

	public Response sendEmailOtp(User user)  throws UserAlreadyPresentException;

	public Response registerUserWithOtp(User user) throws InvalidCredentialException;
	
	public Response verfiyKyc(Long userId);

	public Response kycApplied(Long userId);

	
}
