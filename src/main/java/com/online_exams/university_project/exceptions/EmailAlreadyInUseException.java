package com.online_exams.university_project.exceptions;

@SuppressWarnings("serial")
public class EmailAlreadyInUseException extends RuntimeException {

	public EmailAlreadyInUseException() {
		super("email is already in use please try another email");
	}

}
