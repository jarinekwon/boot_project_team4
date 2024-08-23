package com.boot.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class EmailConfig {

	//
    @Value("${spring.mail.host}")
    private String host;

    //application.properties 파일에서 spring.mail.host 속성의 값을 읽어와 host 필드에 주입
    
    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    /* @Bean */
    /*
    public JavaMailSender javaMailSender() {
    	log.info("@# Start JavaMailSender setting");
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host); //호스트 주소 설정
        mailSender.setPort(port); // 포트 설정
        mailSender.setUsername(username); // 사용자이름 설정
        mailSender.setPassword(password); // 비밀번호 설정 

        Properties props = mailSender.getJavaMailProperties(); 
        props.put("mail.transport.protocol", "smtp"); //이메일 전송 프로토콜로 SMTP 설정
        props.put("mail.smtp.auth", "true");//SMTP 서버 인증을 활성화 
        props.put("mail.smtp.starttls.enable", "true");//SMTP연결을 암호화/ 보안강화
        log.info("@# end JavaMailSender setting");
        return mailSender;
    }
    */
}

//  Spring Boot 애플리케이션에서 이메일 전송
