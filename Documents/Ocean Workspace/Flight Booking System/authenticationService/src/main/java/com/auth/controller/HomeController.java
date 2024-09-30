package com.auth.controller;
 
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@RequestMapping("/user")
public class HomeController {
 
	@GetMapping("/home")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String home() {
		return "Home page";
	}
	@GetMapping("/welcome")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String welcome() {
		return "welcome page";
	}
}