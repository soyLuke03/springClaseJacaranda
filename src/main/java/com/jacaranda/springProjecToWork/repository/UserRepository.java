package com.jacaranda.springProjecToWork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.springProjecToWork.model.User;

public interface UserRepository extends JpaRepository<User, String> {

	List<User> findByEmail(String email);
	
}
