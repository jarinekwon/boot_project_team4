package com.boot.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {
	private Map<String, String> verificationCodes = new HashMap<>();
    private Map<String, Boolean> verifiedEmails = new HashMap<>();

    public String generateCode() {
        // 랜덤 인증 코드 생성
        return String.valueOf(new Random().nextInt(999999));
    }

    public void saveCode(String email, String code) {
        verificationCodes.put(email, code);
        verifiedEmails.put(email, false); // 이메일을 아직 인증되지 않은 상태로 설정
    }

    public boolean verifyCode(String email, String code) {
        String storedCode = verificationCodes.get(email);
        if (code.equals(storedCode)) {
            verifiedEmails.put(email, true); // 이메일을 인증된 상태로 설정
            return true;
        }
        return false;
    }

    public boolean isEmailVerified(String email) {
        return verifiedEmails.getOrDefault(email, false);
    }
}
