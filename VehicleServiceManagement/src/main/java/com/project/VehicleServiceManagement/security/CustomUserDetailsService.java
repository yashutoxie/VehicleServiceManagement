package com.project.VehicleServiceManagement.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.VehicleServiceManagement.entity.Users;
import com.project.VehicleServiceManagement.repo.UserRepository;

 
@Service
	public class CustomUserDetailsService implements UserDetailsService {
 
	    @Autowired
	    private UserRepository userRepository;
 
	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        Users user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	        return new CustomUserDetails(user);
	    }
	}
