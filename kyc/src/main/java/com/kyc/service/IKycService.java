package com.kyc.service;

import org.springframework.web.multipart.MultipartFile;

import com.kyc.entity.Response;

public interface IKycService {
	
	Response addKycDetails(Long userId, String AadhaarNumber, MultipartFile aadhaarPhotoUrl, String dlNumber, MultipartFile dlPhotoUrl, String permanentAddress, String currentAddress);
	
	Response getAllKyc();
	
	Response deleteKyc(Long kycId);
	
	Response deleteKycByUserId(Long userId);
	
	Response getKycByUserId(Long userId);
	
	Response verifyKycByUserId(Long userId,String comment);
}
