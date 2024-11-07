package com.kyc.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.kyc.entity.Response;
import com.kyc.entity.User;


	@FeignClient(name="user-service", url = "http://localhost:9006/user")
	
	public interface UserFeign {





		@GetMapping("/get-by-id/{userId}")
		public Response getUserById(@PathVariable Long userId);
		
		@PutMapping("/verifykyc/{userId}")
	    public User verifyKyc(@PathVariable("userId") Long userId);
	
		@PutMapping("/kycapplied/{userId}")
		public User kycApplied(@PathVariable("userId") Long userId);
}