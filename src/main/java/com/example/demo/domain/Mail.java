package com.example.demo.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class Mail {

	@Autowired
	private JavaMailSender sender;
	
	public void sendEmail(String to, String subject, String text) {
	    	MimeMessagePreparator messagePreparator = mimeMessage -> {
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
	        messageHelper.setTo(to);
	        messageHelper.setSubject(subject);
	        messageHelper.setText(text, true);
	    };
	    try {
	        sender.send(messagePreparator);
	    } catch (MailException e) {
	    }
	}
}
