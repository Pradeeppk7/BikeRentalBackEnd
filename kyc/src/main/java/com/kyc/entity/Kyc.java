package com.kyc.entity;

import com.google.gson.Gson;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="KYC")
@AllArgsConstructor
@NoArgsConstructor
public class Kyc {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	@NotBlank(message = "Please enter Valid Aadhaar Number")
//	@Size(min = 12, message = "Please enter Valid Aadhaar Number")
	@Pattern(
		    regexp = "^\\d{12}$",
		    message = "Please enter Valid Aadhaar Number"
		)
	private String aadhaarNumber;
	
	private String aadhaarPhotoUrl;
	@NotBlank(message = "Please enter Valid Driving License Number")
	private String dlNumber;
	private String dlPhotoUrl;
	@NotBlank(message = "Please enter Valid Permanent Address")
	private String permanentAddress;
	private String currentAddress;
	private String comment;
	 @Override
	    public String toString() {
	        Gson gson = new Gson();
	        return gson.toJson(this);
	    }
}
