package com.online_exams.university_project.services.email;

public interface MailSender {
	void send(String to, String email);
    void send(String to,String subject, String email);
}
