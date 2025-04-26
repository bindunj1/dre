package com.example.dre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.dre.entity.User;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender jms;
	public void sendEmail(User u) {
		
		MimeMessage mm=jms.createMimeMessage();
		try {
			MimeMessageHelper mmh= new MimeMessageHelper(mm, true);
			mmh.setTo(u.getEmail());
			mmh.setSubject("dre account created");
			mmh.setText("Dear "+u.getName()+",your dre  application created successfully");
			jms.send(mm);
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		
	}
}
