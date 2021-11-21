package com.online_exams.university_project.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.online_exams.university_project.entities.Teacher;
import com.online_exams.university_project.repositories.TeacherRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeacherDetailsServiceImp implements UserDetailsService{
	
	private TeacherRepository repository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Teacher> teacherO=this.repository.findByEmail(email);
		if(teacherO.isPresent())return teacherO.get();
		else throw new UsernameNotFoundException("there is no teacher with email :"+email);
	}

}
