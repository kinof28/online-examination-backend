package com.online_exams.university_project.services;


import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.online_exams.university_project.entities.Admin;
import com.online_exams.university_project.exceptions.WrongPasswordException;
import com.online_exams.university_project.repositories.AdminRepository;
import com.online_exams.university_project.requests.AdminUpdateRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServices {
	
	
	private AdminRepository repository;
    private PasswordEncoder passwordEncoder;

	
	public boolean updateData(AdminUpdateRequest request,Admin admin) throws WrongPasswordException {
		Optional<Admin> adminO=this.repository.findById(admin.getId());
		if(adminO.isPresent()) {
			if(this.passwordEncoder.matches(request.getOldPassword(), adminO.get().getPassword())) {
				admin.setEmail(request.getEmail());
				admin.setFirstName(request.getFirstName());
				admin.setLastName(request.getLastName());
				admin.setPassword(this.passwordEncoder.encode(request.getNewPassword()));
				this.repository.save(admin);
				return true;
			}else throw new WrongPasswordException();
		}else {
			return false;
		}
	}
	
}
