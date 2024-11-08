package com.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.model.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {
	
	Otp findByEmail(String email);
	boolean existsByEmail(String email);
	void deleteByEmail(String email);
}
