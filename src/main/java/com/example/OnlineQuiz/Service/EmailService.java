package com.example.OnlineQuiz.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendResultEmail(String toEmail, double finalScore) {
        try {
            String subject = "Quiz Result";
            String body = "Congratulations, you have completed the quiz!\nYour final score is: " + finalScore;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("prasannag1505@gmail.com"); // optional

            mailSender.send(message);
            System.out.println("Result email sent successfully to " + toEmail);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
