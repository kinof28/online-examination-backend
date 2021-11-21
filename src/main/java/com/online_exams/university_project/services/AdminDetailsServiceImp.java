package com.online_exams.university_project.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.online_exams.university_project.entities.Admin;
import com.online_exams.university_project.repositories.AdminRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminDetailsServiceImp implements UserDetailsService {
	
	private AdminRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Admin> adminO=this.repository.findByEmail(email);
		if(adminO.isPresent())return adminO.get();
		else throw new UsernameNotFoundException("there is no admin with email "+email);
	}

}
