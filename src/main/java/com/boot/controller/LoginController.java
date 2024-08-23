package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.CusertbDTO;
import com.boot.dto.MusertbDTO;
import com.boot.dto.PusertbDTO;
import com.boot.service.CusertbService;
import com.boot.service.MusertbService;
import com.boot.service.PusertbService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class LoginController {
	
	@Autowired
	private PusertbService pservice;
	
	@Autowired
	private CusertbService cservice;
	
	@Autowired
	private MusertbService mservice;
	
	SessionController sessioncon = new SessionController();
	
	@RequestMapping("/login")
	public String login() {
		log.info("@# login");
		
		return "login/login";
	}
	@RequestMapping("/clogin_yn")
	public String clogin_ok(@RequestParam HashMap<String, String> param, HttpSession session, RedirectAttributes redirectAttributes) {
		log.info("@# clogin_yn");
		
		ArrayList<CusertbDTO> dtos = cservice.cloginYn(param);
		//log.info("@# dtos.get(0).getCpass()) =>" +dtos.get(0).getCpass());
		if (dtos.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "로그인 실패: 아이디나 비밀번호를 확인해주세요.");
			return "redirect:login";
		} else {
			if (param.get("cpass").equals(dtos.get(0).getCpass())) {
				/* 세션 값 등록*/
				sessioncon.addsession(session, param.get("cuserid"), "c", dtos.get(0).getCusnm());
				
				return "redirect:main";
			} else {
				redirectAttributes.addFlashAttribute("message", "로그인 실패: 아이디나 비밀번호를 확인해주세요.");
				return "redirect:login";
			}
		}
	}
	@RequestMapping("/plogin_yn")
	public String plogin_ok(@RequestParam HashMap<String, String> param, HttpSession session, RedirectAttributes redirectAttributes) {
		log.info("@# plogin_yn");
		
		if(param.get("puserid").equals("admin")) {
			param.put("muserid", param.get("puserid"));
			MusertbDTO dto = mservice.mloginYn(param);
			
			if(dto == null) {
				redirectAttributes.addFlashAttribute("message", "로그인 실패: 아이디나 비밀번호를 확인해주세요.");
				return "redirect:login";
			} else {
				if(param.get("ppass").equals(dto.getMpass())) {
					/* 세션 값 등록*/
					sessioncon.addsession(session, param.get("muserid"), "m", dto.getMname());
					
					return "redirect:main";
				} else {
					redirectAttributes.addFlashAttribute("message", "로그인 실패: 아이디나 비밀번호를 확인해주세요.");
					return "redirect:login";
				}
			}
		} else {
			ArrayList<PusertbDTO> dtos = pservice.ploginYn(param);
			log.info("param.puserid: "+param.get("puserid"));
			log.info("param.pass: "+param.get("ppass"));
			//log.info("@# dtos.get(0).getPpass()) =>" +dtos.get(0).getPpass());
			
			if (dtos.isEmpty()) {
				redirectAttributes.addFlashAttribute("message", "로그인 실패: 아이디나 비밀번호를 확인해주세요.");
				return "redirect:login";
			} else {
				if (param.get("ppass").equals(dtos.get(0).getPpass())) {
					/* 세션 값 등록*/
					sessioncon.addsession(session, param.get("puserid"), "p", dtos.get(0).getPname());
					
					return "redirect:main";
				} else {
					redirectAttributes.addFlashAttribute("message", "로그인 실패: 아이디나 비밀번호를 확인해주세요.");
					return "redirect:login";
				}
			}
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//세션에서 계정 정보 삭제
        //session.removeAttribute("id");
        //session.removeAttribute("usergubun");
        
        //세션을 완전히 무효화
        session.invalidate();
		
		return "redirect:main";
	}
}