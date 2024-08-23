package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.Criteria;
import com.boot.dto.JobaplytbDTO;
import com.boot.dto.JobposttbDTO;
import com.boot.dto.PageDTO;
import com.boot.dto.ResumetbDTO;
import com.boot.dto.SkilltbDTO;
import com.boot.service.JobaplyService;
import com.boot.service.JobposttbService;
import com.boot.service.ResumeService;
import com.boot.service.ShowskillService;
import com.boot.service.SkilltbService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class JobaplytbController {
	@Autowired
	private JobposttbService jobpostservice;
	
	@Autowired
	private JobaplyService Jobaply_service;
	
	@Autowired
	private ResumeService resumeservice;
	
	@Autowired
	private SkilltbService skillservice;
	
	@RequestMapping("/jobaplylist") //jsp이름
	public String jobaplylist(@RequestParam HashMap<String, String> param , Model model, HttpSession session) {
		log.info("@# jobaplylist");

		String cuserid = (String) session.getAttribute("id");
		param.put("cuserid", cuserid);
		
		param.put("csrno", "1");
		
		JobposttbDTO dto = jobpostservice.jobaply(param); //등록된 공고를 가져옴
		model.addAttribute("jobaply", dto); // ("jobaplylist" 모델객체에서 보내는 이름 list 담는내용
		
		ArrayList<JobaplytbDTO> list = Jobaply_service.jobaplylist(param);
		model.addAttribute("jobaplylist", list); // ("jobaplylist" 모델객체에서 보내는 이름 list 담는내용
		
		int count = Jobaply_service.jobaplylist_count(param); //지원 인원
		model.addAttribute("count", count);
		
		return "resume/jobaplylist";
	}
	
	@GetMapping("/resumetb_view") //기업에서 공고에 등록된 이력서 보는 액션
	public String resumetbview(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# resumetb_view");
		log.info("@# param => "+param);
		String cuserid = (String) session.getAttribute("id");
		
		param.put("cuserid", cuserid); //로그인한 계정
		param.put("puserid", param.get("writer")); //이력서 작성자 일반 계정
		
		ArrayList<SkilltbDTO> skill_list = skillservice.select_resume(param);//이력서에 등록된 스킬 목록 조회
		
		Jobaply_service.jobaply_prcnt(param); //이력서 확인 체크
		ResumetbDTO dto = resumeservice.resume_view(param);
		
		model.addAttribute("resumeselect", dto); // ("jobaplylist" 모델객체에서 보내는 이름 list 담는내용
		model.addAttribute("skill_list", skill_list);
		
		return "resume/resumetb_view";
	}
	
	@RequestMapping("/jobaplylist_p") //jsp이름
	public String jobaplylist_p(@RequestParam HashMap<String, String> param, Criteria cri, Model model, HttpSession session) {
		log.info("@# jobaplylist_p");
		String puserid = (String) session.getAttribute("id");
		param.put("puserid", puserid);
		
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		ArrayList<JobaplytbDTO> list = Jobaply_service.jobaplylist_p(param);
		model.addAttribute("jobaplylist_p", list); // ("jobaplylist" 모델객체에서 보내는 이름 list 담는내용
		model.addAttribute("name", (String)session.getAttribute("username"));
		
		//페이징처리
		int total = Jobaply_service.getTotalCount(param); //전체 개수
		log.info("@# total=>"+total);
		model.addAttribute("pageMaker", new PageDTO(total, cri));
		
		return "resume/jobaplylist_p";
	}
	
	@RequestMapping("recruitinfo")
	public String recruitinfo() {
		
		return "recruit/recruitinfo";
	}
}