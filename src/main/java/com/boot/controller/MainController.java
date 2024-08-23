package com.boot.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.ModuleElement;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.CoinfotbDTO;
import com.boot.dto.JobposttbDTO;
import com.boot.dto.ScraptbDTO;
import com.boot.dto.ScribetbDTO;
import com.boot.service.CoinfotbService;
import com.boot.service.JobaplyService;
import com.boot.service.JobposttbService;
import com.boot.service.RecruitService;
import com.boot.service.ScrapService;
import com.boot.service.ScribeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	@Autowired
	private RecruitService service;
	
	@Autowired
	private JobaplyService jobaplyservice;
	
	@Autowired
	private CoinfotbService coinfoservice;
	
	@Autowired
	private ScribeService scribeservice;
	
	@Autowired
	private JobposttbService jobposttbService;
	
	@Autowired
	private ScrapService scrapservice;
	
	@RequestMapping("/")
	public String main() {
		return "redirect:main";
	}
	
	@RequestMapping("/recruitshowform")
	public String recruitshowform(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# recruitshowform");
		log.info("@# param =>"+param);
		String nowid = (String)session.getAttribute("id"); //현재 접속한 아이디
		String usergubun = (String)session.getAttribute("usergubun"); //사용자 구분
		String gubun = "";
		ScribetbDTO scribe = null;
		ScraptbDTO scrap = null;
		
		log.info("@# writer => "+param.get("writer"));//공고 작성자 id(기업)
		param.put("cuserid", param.get("writer"));
		
		JobposttbDTO jobinfoData = service.recruitinfo(param);
		log.info("@# jobinfoData: " + jobinfoData);
		
		CoinfotbDTO coinfo = coinfoservice.Coinfotbinfo(param);
		
		if(usergubun == null) usergubun = "p";
		
		if(usergubun.equals("p")) param.put("puserid", nowid);
		else param.put("cuserid", nowid);
		
		int cnt = jobaplyservice.jobaplycnt(param);
		
		String cuserid = param.get("writer");
		String jobno = param.get("jobno");
		String csrno = param.get("csrno");
		
		if (cuserid != null && jobno != null) {
			jobposttbService.increaseViewCount(cuserid, csrno, jobno);
		}
		
		//로그인 했을 시, 일반 유저 기준 관심 기업 및 공고 스크랩 확인
		if(nowid != null && usergubun.equals("p")) {
			log.info("@# param => "+param);
			scribe = scribeservice.scribe_p_select(param);
			scrap = scrapservice.scrap_p_select(param);
			log.info("@# scribe=> "+scribe);
		}
		
		//로그인 한 계정과 회원 구분에 따른 분류
		if(usergubun.equals("p") && cnt == 0) {
			gubun = "p";
		} else if (usergubun.equals("p") && cnt > 0) {
			gubun = "ps";
		} else if(usergubun.equals("c") && nowid.equals(param.get("writer"))) {
			gubun = "cs";
		} else {
			gubun = "c";
		}
		
		String title = jobinfoData.getJobtitle();
        List<String> keywords = Arrays.asList(title.split(" "));
        List<JobposttbDTO> similarTitles = jobposttbService.getSimilarTitles(keywords);
        model.addAttribute("similarTitles", similarTitles);
		
		model.addAttribute("jobinfoData", jobinfoData);
		model.addAttribute("companyInfo", coinfo);
		model.addAttribute("status", gubun);
		model.addAttribute("usergubun", usergubun);
		model.addAttribute("scribe_tf", scribe==null?"F":"T");
		model.addAttribute("scrap_tf", scrap==null?"F":"T");
		
		log.info("@# gender : " + jobaplyservice.getGenderStatistics(param));
		model.addAttribute("genderStats", jobaplyservice.getGenderStatistics(param));
		
		log.info("@# gender : " + jobaplyservice.getAgeStatistics(param));
		model.addAttribute("ageStats", jobaplyservice.getAgeStatistics(param));
		
		log.info("@# gender : " + jobaplyservice.getEducationStatistics(param));
		model.addAttribute("educationStats", jobaplyservice.getEducationStatistics(param));
		
		return "recruit/recruitinfo";
	}
}