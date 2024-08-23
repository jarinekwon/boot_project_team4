package com.boot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SessionController {
	
	public void addsession(HttpSession session, String id, String gubun, String username) {
		session.setAttribute("id", id);
        session.setAttribute("usergubun", gubun);
        session.setAttribute("username", username);
        
        session.setMaxInactiveInterval(3600);
        log.info(""+(session.getMaxInactiveInterval()/60));
	}
	
	//세션 유지 시간 표현
	@ResponseBody
	@GetMapping("/getSessionTimeout")
    public int getSessionTimeout(HttpServletRequest request, int settime) {
		//log.info("@# getSessionTimeout");
        HttpSession session = request.getSession(false);
        int time = session.getMaxInactiveInterval();
        if (session != null) {
        	//log.info("@# session 시간 => "+session.getMaxInactiveInterval());
            return time - settime; // 세션의 최대 비활성 시간 (초)을 반환
        } else {
            return 0;
        }
    }
}
