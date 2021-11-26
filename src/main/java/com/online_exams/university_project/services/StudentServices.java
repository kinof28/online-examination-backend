package com.online_exams.university_project.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.online_exams.university_project.dtos.StudentDTO;
import com.online_exams.university_project.entities.Student;
import com.online_exams.university_project.entities.StudentConfirmationToken;
import com.online_exams.university_project.mappers.StudentMapper;
import com.online_exams.university_project.repositories.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServices {
	
	private StudentRepository repository;
	private StudentMapper mapper;
	public StudentConfirmationToken createToken(Student student) {
		String token=UUID.randomUUID().toString();
		return new StudentConfirmationToken(token,student);
	}
	public String activateAccount(String token) {
		return "";
	}
	public List<StudentDTO> getAllStudents(){
		return this.mapper.getAllDTOs(this.repository.findAll());
	}
	public boolean deleteStudent(long id) {
		Optional<Student> studentO=this.repository.findById(id);
		if(studentO.isPresent()) {
			this.repository.delete(studentO.get());
			return true;
		}else return false;
	}
}
