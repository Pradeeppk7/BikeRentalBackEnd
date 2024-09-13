package com.kyc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class KycApplication {

	public static void main(String[] args) {
		SpringApplication.run(KycApplication.class, args);
//		System.out.println("HEllo KYC");
	}

}
