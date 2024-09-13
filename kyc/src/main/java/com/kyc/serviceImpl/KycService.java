package com.kyc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.*;

import com.kyc.entity.Kyc;
import com.kyc.entity.Response;
import com.kyc.entity.User;
import com.kyc.exception.OurException;
import com.kyc.feign.UserFeign;
import com.kyc.repo.KycRepository;
import com.kyc.service.AwsS3Service;
import com.kyc.service.IKycService;

@Service
public class KycService implements IKycService{
	
	@Autowired
	private KycRepository kycRepository;
	
	@Autowired
    private AwsS3Service awsS3Service;
	
	  @Autowired 
	    private UserFeign userF;

	@Override
	public Response addKycDetails(Long userId, String aadhaarNumber, MultipartFile aadhaarPhotoUrl, String dlNumber,
			MultipartFile dlPhotoUrl, String permanentAddress, String currentAddress) {
		// TODO Auto-generated method stub
			Response response = new Response();
			try {
			Kyc kyc=new Kyc();
//			Optional<Kyc> kycExist=kycRepository.findByUserId(userId);
//			if(kycExist.isEmpty()) {
//				
//			}
			kyc.setUserId(userId);
			kyc.setAadhaarNumber(aadhaarNumber);
			kyc.setAadhaarPhotoUrl(awsS3Service.saveImageToS3(aadhaarPhotoUrl));
			kyc.setDlNumber(dlNumber);
			kyc.setDlPhotoUrl(awsS3Service.saveImageToS3(dlPhotoUrl));
			kyc.setPermanentAddress(permanentAddress);
			kyc.setCurrentAddress(currentAddress);
//			kycRepository.save(kyc);
			response.setKyc(kycRepository.save(kyc));
			response.setStatusCode(200);
            response.setMessage("successful");
			}catch (Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error saving a Kyc " + e.getMessage());
			}
			return response;
	}


	@Override
	public Response getAllKyc() {
		Response response=new Response();
		try {
		List<Kyc> list = kycRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
			response.setList(list);
			response.setStatusCode(200);
			response.setMessage("successful");
		}
		catch(Exception e) {
			response.setStatusCode(400);
            response.setMessage("Error saving " + e.getMessage());
		}
		return  response;
	}
	
	@Override
	public Response deleteKyc(Long kycId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteKycByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getKycByUserId(Long userId) {
		Response response = new Response();
		try{
			Kyc kyc=kycRepository.findByUserId(userId)
                    .orElseThrow(() -> new OurException("Kyc of the User Not Found"));
			System.err.println(kyc.getUserId());
			response.setKyc(kyc);
			response.setStatusCode(200);
			response.setMessage("successful");
		}
		catch(Exception e) {
			response.setStatusCode(404);
            response.setMessage("Error saving " + e.getMessage());
		}
		return response;
	}

	@Override
	public Response verifyKycByUserId(Long userId,String comment) {
		// TODO Auto-generated method stub
		Response response = new Response();
		try {
		User user=userF.verifyKyc(userId);
		if(comment == null) {
			userF.verifyKyc(userId);
//			System.err.println("KYV Verifififififif");
		}else {
			Kyc kyc=kycRepository.findByUserId(userId).orElseThrow(() -> new OurException("Kyc of the User Not Found"));
			kyc.setComment(comment);
			kycRepository.save(kyc);
			response.setKyc(kyc);
		}
		response.setStatusCode(200);
		response.setMessage("successful");
		
		} catch(Exception e) {
			response.setStatusCode(404);
            response.setMessage("Error saving " + e.getMessage());
		}
		
		return response;
	}


}
