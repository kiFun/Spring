package org.example.notificationservice;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailService {
    final JavaMailSender sender;
    final String from = "noreply@example.org";
    final String url = "localhost";

    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendEmail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        sender.send(message);
    }

    public void sendCreated(String to){
        String subject = "Создание аккаунта";
        String text = "Здравствуйте! Ваш аккаунт на сайте " + url + " был успешно создан.";
        sendEmail(to, subject, text);
    }

    public void sendDeleted(String to){
        String subject = "Удаление аккаунта";
        String text = "Здравствуйте! Ваш аккаунт на сайте " + url + " был удален.";
        sendEmail(to, subject, text);
    }
}
