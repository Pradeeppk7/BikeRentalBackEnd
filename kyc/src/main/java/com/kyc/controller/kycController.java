package com.kyc.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.kyc.entity.Response;
import com.kyc.service.IKycService;

@RestController
@RequestMapping("/kyc")

public class kycController {
	
	@Autowired
	private IKycService kycService;
	
	@PostMapping("/add/{userId}")
	public ResponseEntity<Response> addKycDetails(
			@PathVariable Long userId,
			@RequestParam(value = "dlPhotoUrl", required = false) MultipartFile dlPhotoUrl,
	            @RequestParam(value = "aadhaarNumber", required = false) String aadhaarNumber,
	            @RequestParam(value = "aadhaarPhototUrl", required = false) MultipartFile aadhaarPhototUrl,
	            @RequestParam(value = "dlNumber", required = false) String dlNumber,
	            @RequestParam(value = "currentAddress", required = false) String currentAddress,
	            @RequestParam(value = "permanentAddress", required = false) String permanentAddress
	            ){
		
		Response response =kycService.addKycDetails(userId, aadhaarNumber, aadhaarPhototUrl, dlNumber, dlPhotoUrl, permanentAddress, currentAddress);
		return  ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@PutMapping("/verifykyc/{userId}")
	public ResponseEntity<Response> verifyKycByUserId(
			@PathVariable Long userId,
			@RequestParam(value = "comment", required = false) String comment){
//		System.err.println("test"+comment);
		Response response = kycService.verifyKycByUserId(userId,comment);
		return  ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@GetMapping("/allkyc")
	public  ResponseEntity<Response> getAllKyc(){
//		System.err.println("getting");
		Response response=kycService.getAllKyc();
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@GetMapping("/getkycbyuserid/{userId}")
	public ResponseEntity<Response> getKycByUserId(@PathVariable Long userId){
		Response response=kycService.getKycByUserId(userId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
}
