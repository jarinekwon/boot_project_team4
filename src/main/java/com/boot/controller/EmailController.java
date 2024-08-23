package com.boot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boot.service.EmailService;
import com.boot.service.VerificationCodeService;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
    private EmailService emailService;
    
	@Autowired
    private VerificationCodeService verificationCodeService;

    @PostMapping("/idfind")
    public ResponseEntity<String> sendVerificationCode(@RequestParam String email) {
    	log.info("@# sendVerificationCode");
    	log.info("non create code");
    	String code = verificationCodeService.generateCode();
    	log.info("create code");
    	
    	log.info("non code sendEmail");
    	emailService.sendEmail(email, "이메일 인증 코드", "CODNECT 입니다. 인증 코드는 : " + code+ "입니다.");
    	log.info("code sendEmail");
    	
    	log.info("non code saveCode");
        verificationCodeService.saveCode(email, code);
    	log.info("code saveCode");
    	
        return ResponseEntity.ok("인증번호가 이메일로 전송되었습니다.");
    }

    @PostMapping("/verify") // 인증코드 확인 작업 
    public ResponseEntity<String> verifyCode(@RequestParam String email, @RequestParam String code) {
    	log.info("@# verifyCode");
    	log.info("@# email=>",email);
    	
        boolean isValid = verificationCodeService.verifyCode(email, code);
        
        if (isValid) {
            return ResponseEntity.ok("인증에 성공했습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증번호가 올바르지 않습니다.");
        }
    }
    
    @GetMapping("/status") // 기존 
    public ResponseEntity<Map<String, Boolean>> getEmailVerificationStatus(@RequestParam String email) {
        boolean isVerified = verificationCodeService.isEmailVerified(email);
        
        Map<String, Boolean> response = new HashMap<>();
        response.put("verified", isVerified);
        
        return ResponseEntity.ok(response);
    }
}