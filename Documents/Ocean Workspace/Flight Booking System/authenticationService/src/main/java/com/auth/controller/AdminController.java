package com.auth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.repository.SignUpRepository;
import com.auth.request.SignUpRequest;

@RestController
@RequestMapping("/auth/admin")
@CrossOrigin(origins="*")
public class AdminController {
	@Autowired
	private SignUpRepository repo;

	@GetMapping("/getAllUsers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<SignUpRequest> getAllUsers() {
		List<SignUpRequest> list = new ArrayList<>();
		for (SignUpRequest login : this.repo.findAll()) {
			String role = login.getRole();
			if (role.equals("ROLE_USER")) {
				list.add(login);
			}
		}
		return list;
	}
}
