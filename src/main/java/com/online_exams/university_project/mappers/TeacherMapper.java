package com.online_exams.university_project.mappers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.online_exams.university_project.dtos.TeacherDTO;
import com.online_exams.university_project.entities.Teacher;

@Component
public class TeacherMapper implements Mapper<Teacher, TeacherDTO>{

	@Override
	public TeacherDTO getOneDTO(Teacher teacher) {
		return new TeacherDTO(
				teacher.getEmail(),
				teacher.getFirstName(),
				teacher.getLastName(),
				teacher.getAddress(),
				teacher.getPhoneNumber(),
				teacher.getDepartment().getName(),
				teacher.getSecondDepartment().isPresent()?teacher.getSecondDepartment().get().getName():"");
	}

	@Override
	public List<TeacherDTO> getAllDTOs(List<Teacher> teachers) {
		List<TeacherDTO> teachersDTOs = new LinkedList<>();
		for(Teacher teacher:teachers) {
			teachersDTOs.add(this.getOneDTO(teacher));
		}
		return teachersDTOs;
	}

}
