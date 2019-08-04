package com.energy.consumption.util;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.energy.consumption.model.Email;

/**
* 
* @author Wanessa Nascimento
*
*/
@Service
public class EmailUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);
	
	@Autowired
	private JavaMailSender mailSender;

	public boolean sendMail(Email email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText(email.getMsg());
		message.setSubject(email.getSubject());
		message.setTo(email.getEmail());
		message.setFrom("letsmovieapi@gmail.com");
		try {
			mailSender.send(message);
			return true;
		} catch (Exception e) {
			LOGGER.error(MessageFormat.format("Error to send email {0}", e.getMessage()));
			return false;
		}
	}
}
