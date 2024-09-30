package com.auth.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.auth.repository.SignUpRepository;
import com.auth.request.LoginRequest;
import com.auth.request.SignUpRequest;
import com.auth.response.JwtResponse;
import com.auth.security.JwtUtils;
import com.auth.service.UserDetailsImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/api")
@CrossOrigin(origins="*")
public class AuthController {
	@Autowired
	private AuthenticationManager auth;
	@Autowired
	private JwtUtils helper;
	@Autowired
	private SignUpRepository repo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = this.auth.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = helper.generateToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles));

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signup) {
		if (repo.existsByUsername(signup.getUsername())) {
			return ResponseEntity.badRequest().body("{\"error\": \"Username already exists\"}");
		}
		if (repo.existsByEmail(signup.getEmail())) {
			return ResponseEntity.badRequest().body("{\"error\": \"Email already exists\"}");
		}
		if (signup.getRole() == null) {
			return ResponseEntity.badRequest().body("{\"error\": \"Role cannot be null\"}");
		}
		signup.setPassword(passwordEncoder.encode(signup.getPassword()));
		switch (signup.getRole().toUpperCase()) {
		case "ADMIN":
			signup.setRole("ROLE_ADMIN");
			break;
		case "USER":
			signup.setRole("ROLE_USER");
			break;
		default:
			return ResponseEntity.badRequest().body("Invalid Role");
		}
		repo.save(signup);
		return ResponseEntity.ok("{\"message\": \"User Registered successfully\"}");
	}

	@PutMapping("/updatePassword/{username}/{oldPassword}/{newPassword}")
	public ResponseEntity<?> changePassword(@PathVariable String username, @PathVariable String oldPassword,
			@PathVariable String newPassword) {
		SignUpRequest user = repo.findByUsername(username);
		if (user == null) {
			return ResponseEntity.badRequest().body("User name doesn't exist");
		}
		if (this.passwordEncoder.matches(oldPassword, user.getPassword())) {
			user.setPassword(this.passwordEncoder.encode(newPassword));
			repo.save(user);
			return ResponseEntity.ok("{\"message\": \"Password changed successfully\"}");
		} else {
			return ResponseEntity.badRequest().body("{\"message\": \"Old Password is incorrect\"}");
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody SignUpRequest user) {
		String username = user.getUsername();
		SignUpRequest signup = repo.findByUsername(username);
		if (signup == null) {
			repo.delete(signup);
			repo.save(user);
			return ResponseEntity.ok("Updated successfully " + user);
		} else {
			return ResponseEntity.badRequest().body("User is not present");
		}
	}

	@GetMapping("/currentUser")
	public String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}