package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.CusertbDTO;
import com.boot.dto.PusertbDTO;
import com.boot.service.CusertbService;
import com.boot.service.PusertbService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FindController {

	@Autowired
	private PusertbService pservice;
	
	@Autowired
	private CusertbService cservice;
	
	@RequestMapping("/idfind") // 일반 아이디찾기 
	public String idfind() {
		log.info("@# idfind");
		
		return "find/idfind";
	}
		
	@RequestMapping("/pwfind") // 일반 비밀번호 찾기
	public String pwfind() {
		log.info("@# pwfind");
		
		return "find/pwfind";
	}
	
	@RequestMapping("/ppwchange")// 일반 비밀번호 변경
	public String ppwchange() {
		log.info("@# ppwchange");
		
		return "pwchange/ppwchange";
	}
	
	@RequestMapping("/cpwchange")// 기업 비밀번호 변경 
	public String cpwchange() {
		log.info("@# cpwchange");
		
		return "pwchange/cpwchange";
	}
	
	@RequestMapping("/pidfind_yn") // 일반 아이디 찾기 
	public String pidfind_ok(@RequestParam HashMap<String, String> param,  Model model) {
		log.info("@# pidfind_yn");
		
		log.info("param: " + param);  
		 	 
	    ArrayList<PusertbDTO> dtos = pservice.pidfindYn(param);
		log.info("param.pname: "+param.get("pname"));
		log.info("param.email: "+param.get("email"));
		
		if (dtos.isEmpty()) {
			model.addAttribute("message", "로그인 실패: 아이디나 이메일을 확인해주세요.");
			return "redirect:idfind";
		} else {
			PusertbDTO puser = dtos.get(0);
		 	log.info("puser: "+ puser);
	        model.addAttribute("pname", puser.getPname());
	        model.addAttribute("email", puser.getEmail());
	        model.addAttribute("puserid", puser.getPuserid());
	        
			log.info(" puser.getPname(): "+ puser.getPname());
			return "view/pidview";  
		}
	}

	@PostMapping("/ppwfind_yn") // 일반 비밀번호 찾기
	 public String ppwfind_ok(@RequestParam HashMap<String, String> param, Model model) {
		pservice.pupdatePassword(param);
			
	   // 성공 메시지 전달 및 리디렉션
		model.addAttribute("message", "Password updated successfully.");
        return "redirect:login";
	}
	
	@RequestMapping("/cidfind_yn")    // 기업 아이디 찾기 
	public String cidfind_ok(@RequestParam HashMap<String, String> param,  Model model) {
		log.info("@# cidfind_yn");
		
		log.info("param: " + param); 
		
		ArrayList<CusertbDTO> dtos = cservice.cidfindYn(param);
		log.info("param.cusnm: "+param.get("cusnm"));
		log.info("param.bsnum: "+param.get("bsnum"));
		log.info("param.cmail: "+param.get("cmail"));
		
		log.info("dtos.dtos: "+dtos);
		
		if (dtos.isEmpty()) {
			model.addAttribute("message", "아이디나 이메일을 확인해주세요.");
			return "redirect:idfind";
		} else {
			CusertbDTO cuser = dtos.get(0);
			log.info("cuser: "+ cuser);
			model.addAttribute("cusnm", cuser.getCusnm());
			model.addAttribute("bsnum", cuser.getBsnum());
			model.addAttribute("cmail", cuser.getCmail());
			model.addAttribute("cuserid", cuser.getCuserid());
			
			log.info(" cuser.getBsnum(): "+ cuser.getBsnum());
			return "view/cidview"; 
		}
	}
	
	@PostMapping("/cpwfind_yn") // 기업 비밀번호 찾기
	public String cpwfind_ok(@RequestParam HashMap<String, String> param, Model model) {
		cservice.cupdatePassword(param);
		// 성공 메시지 전달 및 리디렉션
		model.addAttribute("message", "비밀번호 변경에 성공했습니다.");
		
		return "redirect:login";
	}
}