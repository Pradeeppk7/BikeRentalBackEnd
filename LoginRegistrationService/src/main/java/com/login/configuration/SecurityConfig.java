package com.login.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.login.service.CustomUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;


	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		 
		 http.csrf(c -> c.disable())
		.authorizeHttpRequests(
				request -> request.requestMatchers("/user/getUser/**").permitAll()
				.requestMatchers("/user/validate").permitAll()
				.requestMatchers("/user/delete/**").permitAll()
				.requestMatchers("/user/register").permitAll()
				.requestMatchers("/user/get-by-id/**").permitAll()
				.requestMatchers("/user/all").permitAll()
				.requestMatchers("/user/sendotp").permitAll()
				.requestMatchers("/user/registerwithotp").permitAll()
				.requestMatchers("/user/verifykyc/**").permitAll()
				.requestMatchers("/user/kycapplied/**").permitAll()
				.requestMatchers("/user/login").permitAll().anyRequest().authenticated());
		return http.build();
	}
	 
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider =new DaoAuthenticationProvider();
		
		authenticationProvider.setUserDetailsService(customUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(authenticationProvider());
	}
}
