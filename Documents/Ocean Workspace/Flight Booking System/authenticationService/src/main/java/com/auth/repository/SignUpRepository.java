package com.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.request.SignUpRequest;

@Repository
public interface SignUpRepository extends JpaRepository<SignUpRequest, String> {

	public SignUpRequest findByUsername(String username);

	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);
}