package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
/*
@Transactional
@RequiredArgsConstructor
*/
public class EmailService {
	
	@Autowired
    private JavaMailSender mailSender;
	
    public void sendEmail(String to, String subject, String text) {
    	SimpleMailMessage message = new SimpleMailMessage();
    	
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        log.info("start mailSender.send(message)");
        mailSender.send(message);
        log.info("end mailSender.send(message)");
    }
}