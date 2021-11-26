package com.online_exams.university_project.exceptions;

@SuppressWarnings("serial")
public class BadCredentialsException extends RuntimeException{

	public BadCredentialsException() {
		super("Your JWT token Was Corrupted please try and login again");
	}
	

}
