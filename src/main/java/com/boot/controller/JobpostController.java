package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boot.dao.WrktygbtbDAO;
import com.boot.dto.CareertbDTO;
import com.boot.dto.Criteria;
import com.boot.dto.EdugbtbDTO;
import com.boot.dto.JobposttbDTO;
import com.boot.dto.PageDTO;
import com.boot.dto.WrktygbtbDTO;
import com.boot.service.CareertbService;
import com.boot.service.EdugbtbService;
import com.boot.service.JobposttbService;
import com.boot.service.WrktygbtbService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class JobpostController {
	
	@Autowired
	private JobposttbService service;
	
	@Autowired
	private EdugbtbService eduservice;
	
	@Autowired
	private CareertbService careerservice;
	
	@Autowired
	private WrktygbtbService wrktyservice;
	
	@RequestMapping("/jobpost")
	public String jobpost(@RequestParam HashMap<String, String> param, Criteria cri, Model model) {
		log.info("@# jobpost");
		log.info("@# cri =>"+cri);
		
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		ArrayList<JobposttbDTO> list = service.jobselect(param);
		int total = service.getCount(param);
		log.info("@# total=>"+total);

		//검색 조건 테이블 조회 쿼리
		ArrayList<EdugbtbDTO> edu = eduservice.selectAll();//학력
		ArrayList<CareertbDTO> career = careerservice.selectAll();//경력
		ArrayList<WrktygbtbDTO> wrkty = wrktyservice.selectAll();//근무형태
		
		//검색 박스 값
		model.addAttribute("edu", edu);
		model.addAttribute("career", career);
		model.addAttribute("wrkty", wrkty);
		
		model.addAttribute("joblist", list); //목록 리스트
		model.addAttribute("pageMaker", new PageDTO(total, cri)); //페이징
		
		return "jobpost/jobpost";
	}
	
	@RequestMapping("/jobpostSearch")
	public String jobpostSearch(@RequestParam HashMap<String, String> param, Criteria cri, Model model) {
		log.info("@# jobpostSearch");
	
		ArrayList<JobposttbDTO> list = service.jobselect(param);
		
		//jsp단의 hidden값에 세팅
		model.addAttribute("job_s", param.get("job_s"));
		model.addAttribute("loc_s", param.get("loc_s"));
		model.addAttribute("career_s", param.get("career_s"));
		model.addAttribute("edu_s", param.get("edu_s"));
		model.addAttribute("wrkty_s", param.get("wrkty_s"));
		
		model.addAttribute("joblist", list);
		return "jobpost/jobpost";
	}
	
	@RequestMapping("/jobpostSearchAjax")
	public ModelAndView jobpostSearchAjax(@RequestParam HashMap<String, String> param, Criteria cri) { //, Model model) {
		log.info("@# jobpostSearchAjax");
		ModelAndView mav = new ModelAndView();
		//mav.setViewName("jobpost_ajax");
		mav.setViewName("jobpost/jobpost_ajax");
		
		log.info("@# cri =>"+cri);
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		ArrayList<JobposttbDTO> list = service.jobselect(param);
		int total = service.getCount(param);
		log.info("@# total=>"+total);

		//jsp단의 hidden값에 세팅
		mav.addObject("job_s", param.get("job_s"));
		mav.addObject("loc_s", param.get("loc_s"));
		mav.addObject("career_s", param.get("career_s"));
		mav.addObject("edu_s", param.get("edu_s"));
		mav.addObject("wrkty_s", param.get("wrkty_s"));
		log.info("@# list => "+list);
		mav.addObject("joblist", list);
		mav.addObject("pageMaker", new PageDTO(total, cri));
		
		return mav;
	}

	@GetMapping("jobpostPageAjax")
	public ModelAndView jobpostPageAjax(@RequestParam HashMap<String, String> param, Criteria cri) {
		log.info("@# jobpostSearchAjax");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jobpost/jobpost_ajax");
		
		log.info("@# cri =>"+cri);
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		ArrayList<JobposttbDTO> list = service.jobselect(param);
		int total = service.getCount(param);
		log.info("@# total=>"+total);
		
		mav.addObject("job_s", param.get("job_s"));
		mav.addObject("loc_s", param.get("loc_s"));
		mav.addObject("career_s", param.get("career_s"));
		mav.addObject("edu_s", param.get("edu_s"));
		mav.addObject("wrkty_s", param.get("wrkty_s"));
		
		mav.addObject("joblist", list);
		mav.addObject("pageMaker", new PageDTO(total, cri));
		return mav;
	}
	
	@RequestMapping("/jobpostRestAjax")
	public ModelAndView jobpostRestAjax(@RequestParam HashMap<String, String> param, Criteria cri) {
		log.info("@# jobpostRestAjax");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jobpost/jobpost_ajax");
		
		log.info("@# cri =>"+cri);
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		ArrayList<JobposttbDTO> list = service.jobselect(param);
		int total = service.getCount(param);
		log.info("@# total=>"+total);

		mav.addObject("joblist", list);
		mav.addObject("pageMaker", new PageDTO(total, cri));
		return mav;
	}
	
	@RequestMapping("main")
	public String list(Model model) {
		log.info("@# main");
		
		ArrayList<JobposttbDTO> list = service.mainlist();
		ArrayList<JobposttbDTO> allList = service.mainAllList();
		
		model.addAttribute("mainAllList", allList);
		model.addAttribute("main", list);
		
		return "main";
	}
	
	@RequestMapping("/rviewrecruitlist")
	public String rviewrecruitlist(@RequestParam HashMap<String, String> param, Criteria cri, Model model) {
		log.info("@# rviewrecruitlist");
		
		List<JobposttbDTO> list = service.rviewrecruitlist(param);
		
		param.put("pageNum", cri.getPageNum() + "");
		param.put("amount", cri.getAmount() + "");
		int total = service.getTotalCount(param);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", new PageDTO(total, cri));
		
		return "rview/rviewrecruitlist";
	}
}
