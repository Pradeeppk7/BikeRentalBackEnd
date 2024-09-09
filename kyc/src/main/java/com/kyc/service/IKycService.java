package com.kyc.service;

import com.kyc.entity.Response;

public interface IKycService {
	
	Response addKycDetails(Long userId, String AadhaarNumber, String aadhaarPhotoUrl, String dlNumber, String dlPhotoUrl, String permanentAddress, String currentAddress);
	
	Response deleteKyc(Long kycId);
	
	Response deleteKycByUserId(Long userId);
	
	Response getKycByUserId(Long userId);
	
	Response verifyKyc(Long userId);
}
