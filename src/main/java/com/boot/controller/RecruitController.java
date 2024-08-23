package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dao.JobposttbDAO;
import com.boot.dto.CareertbDTO;
import com.boot.dto.CoinfotbDTO;
import com.boot.dto.EdugbtbDTO;
import com.boot.dto.JobposttbDTO;
import com.boot.dto.ScribetbDTO;
import com.boot.dto.ShowskilltbDTO;
import com.boot.dto.SkilltbDTO;
import com.boot.dto.WrktygbtbDTO;
import com.boot.service.CareertbService;
import com.boot.service.CoinfotbService;
import com.boot.service.EdugbtbService;
import com.boot.service.JobaplyService;
import com.boot.service.RecruitService;
import com.boot.service.ScribeService;
import com.boot.service.ShowskillService;
import com.boot.service.SkilltbService;
import com.boot.service.WrktygbtbService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RecruitController {
	
	@Autowired
	private RecruitService service;
	
	@Autowired
	private CareertbService Cservice;
	
	@Autowired
	private EdugbtbService Eservice;
	
	@Autowired
	private WrktygbtbService Wservice;
	
	@Autowired
	private JobaplyService jobaplyservice;
	
	@Autowired
	private CoinfotbService coinfoservice;
	
	@Autowired
	private ScribeService scribeservice;
	
	@Autowired
    private ShowskillService showskillservice;
    
    @Autowired
    private SkilltbService skillservice;
	
	@RequestMapping("/recruitadd")
	public String recruitadd(Model model) {
		log.info("@# recruitadd");
		
		ArrayList<CareertbDTO> cdto = Cservice.selectAll(); //경력 구분
		ArrayList<EdugbtbDTO> edto = Eservice.selectAll(); //학력 구분
		ArrayList<WrktygbtbDTO> wdto = Wservice.selectAll(); //고용 형태
		
		model.addAttribute("prsup", cdto); //경력
		model.addAttribute("educa", edto); //학력
		model.addAttribute("wrkty", wdto); //고용 형태
		
		return "recruit/recruitadd";
	}

	@GetMapping("/recruitmodify")
	public String recruitmodify(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# recruitmodify");
		
		String cuserid = (String) session.getAttribute("id");
		
		param.put("cuserid", cuserid);
		
		log.info("@# cuserid: "+cuserid);
		log.info("@# csrno=> "+param.get("csrno"));
		log.info("@# jobno=> "+param.get("jobno"));
		
		ArrayList<CareertbDTO> cdto = Cservice.selectAll(); //경력 구분
		ArrayList<EdugbtbDTO> edto = Eservice.selectAll(); //학력 구분
		ArrayList<WrktygbtbDTO> wdto = Wservice.selectAll(); //고용 형태
		
		model.addAttribute("prsup", cdto); //경력
		model.addAttribute("educa", edto); //학력
		model.addAttribute("wrkty", wdto); //고용 형태
		
		JobposttbDTO dto = service.recruitinfo(param);
		log.info("recruitinfo => "+dto);
		model.addAttribute("recruitlist", dto);
		
		return "recruit/recruitmodify";
	}

	@RequestMapping("/recruitinsert")
	public String recruitadd(@RequestParam HashMap<String, String> param, Model model, HttpSession session, String defaultValue) {
		log.info("@# recruitinsert");
		
		String cuserid = (String) session.getAttribute("id");
		
		param.put("cuserid", cuserid);
		param.put("csrno", "1");
		
		if (param.get("salary") == null || param.get("salary").trim().isEmpty()) {
			param.put("salary", defaultValue);
		}
		
		if (param.get("recno") == null || param.get("recno").trim().isEmpty()) {
			param.put("recno", "0");
		}
		
		service.recruitadd(param);

		JobposttbDTO jobinfoData = service.recruitinfo(param);
		log.info("@# jobinfoData: " + jobinfoData);
		model.addAttribute("jobinfoData", jobinfoData);

		return "redirect:recruitlist";
	}
	
	@GetMapping("/recruitshow")
	public String recruitshow(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# recruitshow");

		String cuserid = (String)session.getAttribute("id");
		String usergubun = (String)session.getAttribute("usergubun");
		param.put("cuserid", cuserid);
		
		log.info("@# cuserid => "+ cuserid);
		log.info("@# csrno => "+ param.get("csrno"));
		log.info("@# jobno => "+ param.get("jobno"));
		
		JobposttbDTO jobinfoData = service.recruitinfo(param);
		
		CoinfotbDTO coinfo = coinfoservice.Coinfotbinfo(param);
		
		log.info("@# jobinfoData: " + jobinfoData);
		model.addAttribute("jobinfoData", jobinfoData);
		model.addAttribute("companyInfo", coinfo);
		model.addAttribute("status", "cs");
		model.addAttribute("usergubun", usergubun);
		model.addAttribute("minileft", "F");
		
		log.info("@# gender : " + jobaplyservice.getGenderStatistics(param));
		model.addAttribute("genderStats", jobaplyservice.getGenderStatistics(param));
		
		log.info("@# gender : " + jobaplyservice.getAgeStatistics(param));
		model.addAttribute("ageStats", jobaplyservice.getAgeStatistics(param));
		
		log.info("@# gender : " + jobaplyservice.getEducationStatistics(param));
		model.addAttribute("educationStats", jobaplyservice.getEducationStatistics(param));
		
		return "recruit/recruitinfo";
	}
	
	@RequestMapping("/recruitlist")
	public String recruitlist(HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# Displaying recruit list");
		
		String cuserid = (String) session.getAttribute("id");
		param.put("cuserid", cuserid);
		
		ArrayList<JobposttbDTO> jobinfoList = service.recruitlist(param);
		
		log.info("@# jobinfoList: " + jobinfoList);
		model.addAttribute("list", jobinfoList);
		
		return "recruit/recruitlist";
	}
	
	@GetMapping("/recruitupdate")
    public String updateRecruit(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
        log.info("@# recruitmodify");
        log.info("@# param=>"+param);
        
        String cuserid = (String)session.getAttribute("id");
		param.put("cuserid", cuserid);
		
		//급여 미 입력 시, 0 처리
		if(param.get("salary").equals("") || param.get("salary") == null) param.put("salary", "0");

        service.recruitupdate(param);
        
        model.addAttribute("csrno", param.get("csrno"));
        model.addAttribute("jobno", param.get("jobno"));
        
        return "redirect:recruitshow?csrno="+param.get("csrno")+"&jobno="+param.get("jobno");
	}
	
	@RequestMapping("/recruitdelete")
	public String deleteRecruit(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# recruitdelete");
		log.info("@# param=>"+param);
		
		String cuserid = (String)session.getAttribute("id");
		param.put("cuserid", cuserid);
		
		//공고 이력 삭제
		service.recruitdelete(param);
		
		//해당 공고에 지원한 이력 삭제(jobaplytb)
		jobaplyservice.jobaply_delete(param);
		
		return "redirect:recruitlist";
	}
	
	@RequestMapping("/recruitinfo_p")
	public String recruitshow_p(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# recruitshow_p");

		String puserid = (String)session.getAttribute("id");
		String usergubun = (String)session.getAttribute("usergubun");
		ScribetbDTO scribe = null;
		
		param.put("puserid", puserid);
		
		//로그인 했을 시, 일반 유저 기준
		if(puserid != null && usergubun.equals("p")) {
			log.info("@# param => "+param);
			param.put("writer", param.get("cuserid"));
			scribe = scribeservice.scribe_p_select(param);
			log.info("@# scribe=> "+scribe);
		}
		
		log.info("@# cuserid => "+ puserid);
		log.info("@# csrno => "+ param.get("csrno"));
		log.info("@# jobno => "+ param.get("jobno"));
		
		JobposttbDTO jobinfoData = service.recruitinfo(param);
		CoinfotbDTO coinfo = coinfoservice.Coinfotbinfo(param);
		
		log.info("@# jobinfoData: " + jobinfoData);
		model.addAttribute("jobinfoData", jobinfoData);
		model.addAttribute("companyInfo", coinfo);
		model.addAttribute("status", "ps");
		model.addAttribute("scribe_tf", scribe==null?"F":"T");
		model.addAttribute("minileft", "F");
		
		log.info("@# gender : " + jobaplyservice.getGenderStatistics(param));
		model.addAttribute("genderStats", jobaplyservice.getGenderStatistics(param));
		
		log.info("@# gender : " + jobaplyservice.getAgeStatistics(param));
		model.addAttribute("ageStats", jobaplyservice.getAgeStatistics(param));
		
		log.info("@# gender : " + jobaplyservice.getEducationStatistics(param));
		model.addAttribute("educationStats", jobaplyservice.getEducationStatistics(param));
		
		return "recruit/recruitinfo";
	}
	
	@GetMapping("/skillPopup")
	public String skillPopup(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		List<ShowskilltbDTO> showskilldto = showskillservice.selectAll();
		List<SkilltbDTO> skilldto = skillservice.select_resume(param);
		
		model.addAttribute("showskilldto", showskilldto); //스킬 출력 목록
		model.addAttribute("skilldto", skilldto); //선택한 스킬 출력
			
	     return "recruit/skillPopup";
	 }
}