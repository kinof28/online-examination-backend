package com.online_exams.university_project.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.online_exams.university_project.dtos.TeacherDTO;
import com.online_exams.university_project.entities.Teacher;
import com.online_exams.university_project.entities.TeacherConfirmationToken;
import com.online_exams.university_project.mappers.TeacherMapper;
import com.online_exams.university_project.repositories.TeacherRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeacherServices {
	
	private TeacherRepository repository;
	private TeacherMapper mapper;
	
	public TeacherConfirmationToken createToken(Teacher teacher) {
		String token = UUID.randomUUID().toString();
		return new TeacherConfirmationToken(token, teacher);
	}
	public String activateAccount(String token) {
		return "";
	}
	public List<TeacherDTO> getAllTeachers(){

		return this.mapper.getAllDTOs(this.repository.findAllByIsActivatedIsTrueAndIsLockedIsFalse());

	}
	public boolean deleteTeacher(long id) {
		Optional<Teacher> teacherO=this.repository.findById(id);
		if(teacherO.isPresent()) {
			Teacher teacher =teacherO.get();
			teacher.setLocked(true);
			this.repository.save(teacher);
			return true;
		}else return false;
	}
}
