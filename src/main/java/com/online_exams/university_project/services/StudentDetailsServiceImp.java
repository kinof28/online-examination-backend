package com.online_exams.university_project.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.online_exams.university_project.entities.Student;
import com.online_exams.university_project.repositories.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentDetailsServiceImp implements UserDetailsService {
	private StudentRepository repository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Student> studentO=this.repository.findByEmail(email);
		if(studentO.isPresent())return studentO.get();
		else throw new UsernameNotFoundException("there is no student with email :"+email);
	}

}
