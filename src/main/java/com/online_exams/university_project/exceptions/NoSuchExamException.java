package com.online_exams.university_project.exceptions;

@SuppressWarnings("serial")
public class NoSuchExamException extends RuntimeException{
    public NoSuchExamException() {
        super("no such an exam with that id please try another one");
    }
}
