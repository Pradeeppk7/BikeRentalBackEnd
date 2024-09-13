package com.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableFeignClients
@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication
//@EnableJpaRepositories ("com.login.repositroy.UserRepository")
public class LoginRegistrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginRegistrationServiceApplication.class, args);
	}

}
