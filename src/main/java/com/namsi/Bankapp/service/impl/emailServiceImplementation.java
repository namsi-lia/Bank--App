package com.namsi.Bankapp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.namsi.Bankapp.dto.EmailData;

@Service

public class emailServiceImplementation implements EmailService {
      
    @Autowired
	private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")

    private String senderMail;


    @Override
    public void sendmailAlert(EmailData emailData) {
        try{
            SimpleMailMessage mailMessage =new SimpleMailMessage();
            mailMessage.setFrom(senderMail);
            mailMessage.setTo(emailData.getRecipient());
            mailMessage.setText(emailData.getMessageBody());
            mailMessage.setSubject(emailData.getSubject());

			javaMailSender.send(mailMessage);
			System.out.println("Email sent successfully");
        }catch(MailException e){
            throw new RuntimeException(e);
        }
           }



    
    
}
