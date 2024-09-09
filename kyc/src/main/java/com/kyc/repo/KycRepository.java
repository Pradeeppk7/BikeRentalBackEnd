package com.kyc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kyc.entity.Kyc;

public interface KycRepository extends JpaRepository<Kyc,Long> {

}
