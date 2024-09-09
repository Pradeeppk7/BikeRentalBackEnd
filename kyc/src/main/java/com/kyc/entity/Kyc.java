package com.kyc.entity;

import java.math.BigDecimal;
import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Transient;

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
	private String aadhaarNumber;
	private String aadhaarPhototUrl;
	private String dlNumber;
	private String dlPhotoUrl;
	private String permanentAddress;
	private String currentAddress;
	 @Override
	    public String toString() {
	        Gson gson = new Gson();
	        return gson.toJson(this);
	    }
}
