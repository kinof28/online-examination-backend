package com.online_exams.university_project.mappers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.online_exams.university_project.dtos.StudentDTO;
import com.online_exams.university_project.entities.Option;
import com.online_exams.university_project.entities.Student;

@Component
public class StudentMapper implements Mapper<Student, StudentDTO> {

	@Override
	public StudentDTO getOneDTO(Student student) {
		List<String> activeOptions=new LinkedList<String>();
		for(Option o:student.getActiveOptions()) {
			activeOptions.add(o.getName());
		}
		return new StudentDTO(
				student.getId(),
				student.getEmail(),
				student.getFirstName(),
				student.getLastName(),
				student.getAddress(),
				student.getPhoneNumber(),
				student.getDepartment().getName(),
				student.getDegree().getName(),
				activeOptions);
	}

	@Override
	public List<StudentDTO> getAllDTOs(List<Student> students) {
		List<StudentDTO> studentsDTOs=new LinkedList<>();
		for(Student student:students) {
			studentsDTOs.add(this.getOneDTO(student));
		}
		return studentsDTOs;
	}

}
