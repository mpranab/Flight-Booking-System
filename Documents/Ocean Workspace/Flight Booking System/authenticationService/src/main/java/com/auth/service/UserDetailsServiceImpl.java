package com.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth.repository.SignUpRepository;
import com.auth.request.SignUpRequest;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	SignUpRepository repo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SignUpRequest user = repo.findByUsername(username);
		if (user != null) {
			System.out.println(user.getUsername());
			return UserDetailsImpl.getUser(user);
		} else {
			throw new UsernameNotFoundException("username not found: " + username);
		}
	}

}