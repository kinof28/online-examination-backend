package com.online_exams.university_project.services.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService implements MailSender{

	private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setText(email, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject("Confirm your email in Live With ME");
            mimeMessageHelper.setFrom("Abdo@Hamadouche.mascara");
            this.mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("Some thing went wrong here -- : " + e);
        }
    }
    @Override
    @Async
    public void send(String to,String subject, String email) {
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setText(email, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom("Abdo@Hamadouche.mascara");
            this.mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("Some thing went wrong here -- : " + e);
        }
    }

}
