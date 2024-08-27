package com.rental.RentalApplication;
import org.modelmapper.ModelMapper; 
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableDiscoveryClient
//@EnableJpaAuditing
@SpringBootApplication
//@EnableJpaRepositories(basePackages="com.rental.RentalApplication.repository")
//@ComponentScan(basePackages="com.*")
public class VehicleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleServiceApplication.class, args);
	}
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
