package com.online_exams.university_project.dtos;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeacherDTO {
	private String email;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private String department;
	private String secondDepartment;

}
