package com.login.dto;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


	private Long id;
	
	
	private String name;
	
	private String email;
	
	private String password;
	private String phoneNumber;
	private String role;
	
	private Date createdAt;
	

	private Date updatedAt;
	
}
