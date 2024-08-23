package com.boot.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.service.CusertbService;
import com.boot.service.PusertbService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RegisterController {
	
	@Autowired
	private PusertbService pservice;
	
	@Autowired
	private CusertbService cservice;
	
	//jsp register
	@RequestMapping("/register")
	public String register() {
		log.info("@# register");
		return "login/register";
	}
	
	@RequestMapping("/pRegisterInsert")
	public String pRegisterInsert(@RequestParam HashMap<String, String> param) {
		log.info("@# pRegisterInsert");
		pservice.pRegisterInsert(param);
//		String gubun = param.get("gubun");
		return "login/login";
	}
	@RequestMapping("/cRegisterInsert")
	public String cRegisterInsert(@RequestParam HashMap<String, String> param) {
		log.info("@# cRegisterInsert");
		cservice.cRegisterInsert(param);
		
		return "login/login";
	}
	
	@RequestMapping("/idchk")
	@ResponseBody
	public ResponseEntity<Object> idchk(@RequestParam String id, @RequestParam String gubun) {
		log.info("@# idchk");
        String message = "";
		int cnt = 0;
		String flag = "f";
        if(gubun.equals("p")) { //일반 유저
        	cnt = pservice.idchk(id);
        } else {  //기업 유저
        	cnt = cservice.idchk(id);
        }
        
        if(cnt > 0) {
        	message = "입력하신 아이디는 현재 사용 중 입니다. 다른 아이디를 입력하세요.";
        	
        } else {
        	message = "현재 입력하신 아이디는 사용이 가능합니다.";
        	flag = "t";
        }
		
		return ResponseEntity.ok().body("{\"message\": \""+message+"\", \"flag\": \""+flag+"\", \"gubun\": \""+gubun+"\"}");
	}
}
