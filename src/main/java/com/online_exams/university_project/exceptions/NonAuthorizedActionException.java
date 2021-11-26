package com.online_exams.university_project.exceptions;

@SuppressWarnings("serial")
public class NonAuthorizedActionException extends RuntimeException{
    public NonAuthorizedActionException() {
        super("You Are Attempting to do a Non Authorised Action");
    }
}
