package com.online_exams.university_project.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseSubscribeRequest {
	
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private Long departmentId;


}
