package com.online_exams.university_project.exceptions;

@SuppressWarnings("serial")
public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException() {
        super("wrong password please try another time");
    }
}
