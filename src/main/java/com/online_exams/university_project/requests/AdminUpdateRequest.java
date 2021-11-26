package com.online_exams.university_project.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AdminUpdateRequest {
	private String oldPassword;
	private String email;
	private String newPassword;
	private String firstName;
	private String lastName;

}
