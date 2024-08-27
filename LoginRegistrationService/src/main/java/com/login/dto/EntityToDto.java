package com.login.dto;

import com.login.model.User;

public class EntityToDto {

	public static UserDto convertToDto(User user) {
		// TODO Auto-generated method stub
		return new UserDto(user.getId(),user.getName(),user.getEmail(),user.getPassword(),user.getPhoneNumber(),user.getRole(),user.getCreatedAt(),user.getUpdatedAt());
		
	}

	
}
