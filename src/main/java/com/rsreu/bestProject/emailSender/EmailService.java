package com.rsreu.bestProject.emailSender;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.sender.email}")
    private String senderEmail;

    public void sendSimpleEmail(String receiver, String code){
       var message = new SimpleMailMessage();

       message.setFrom(senderEmail);
       message.setTo(receiver);
       message.setSubject("Confirmation code");
       message.setText("Your confirmation code: \n" + code);
       message.setReplyTo("no-reply@null.moe");
       javaMailSender.send(message);
    }

}
