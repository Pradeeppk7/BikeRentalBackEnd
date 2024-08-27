package com.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);

	User findByNameOrEmail(String name,String email);

//	boolean existsByEmail(String email);
	
	User findByEmail(String email);

	boolean existsByEmail(String email);

}
