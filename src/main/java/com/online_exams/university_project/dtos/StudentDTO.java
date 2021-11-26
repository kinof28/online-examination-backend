package com.online_exams.university_project.dtos;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentDTO {
	
	private String email;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private String department;
	private String degree;
	private List<String> activeOptions;


}
