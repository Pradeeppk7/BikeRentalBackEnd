package com.kyc.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import com.kyc.entity.Kyc;
import com.kyc.entity.Response;

public interface KycRepository extends JpaRepository<Kyc,Long> {

	Optional<Kyc> findByUserId(Long userId);
	
}
