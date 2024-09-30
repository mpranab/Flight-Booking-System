package com.auth.request;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "sign_up")
public class SignUpRequest {

	@Id
	@Column(name = "user_name")
	@NotBlank(message = "UserName is required")
	private String username;
	@NotBlank(message = "Email is required")
	@Email(message = "Please provide correct format")
	@Column(name = "email")
	private String email;
	@NotBlank(message = "Password is required")
	@Column(name = "password")
	private String password;
	@NotBlank(message = "Role is required")
	@Column(name = "role")
	private String role;

	public SignUpRequest(String username, String email, String password, String role) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
