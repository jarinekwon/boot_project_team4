package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.boot.dao.JobsorttbDAO;
import com.boot.dto.Criteria;
import com.boot.dto.ImgtbDTO;
import com.boot.dto.JobsorttbDTO;
import com.boot.dto.PageDTO;
import com.boot.dto.PusertbDTO;
import com.boot.dto.RcareerDTO;
import com.boot.dto.ResumetbDTO;
import com.boot.dto.RviewtbDTO;
import com.boot.dto.ShowskilltbDTO;
import com.boot.dto.SkilltbDTO;
import com.boot.service.ImgtbService;
import com.boot.service.JobaplyService;
import com.boot.service.JobsorttbService;
import com.boot.service.PusertbService;
import com.boot.service.RcareerService;
import com.boot.service.ResumeService;
import com.boot.service.RviewService;
import com.boot.service.ShowskillService;
import com.boot.service.SkilltbService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ResumeController {

    @Autowired
    private ResumeService service;
    
    @Autowired
    private PusertbService Pservice;
    
    @Autowired
    private ShowskillService showskillservice;
    
    @Autowired
    private SkilltbService skillservice;
    
    @Autowired
    private ImgtbService imgservice;
    
    @Autowired
    private ServletContext servletContext; //ServletContext 주입
    
    @Autowired
    private JobaplyService jobaplyservice;
    
    @Autowired
    private JobsorttbService jobsortservice; 
    
    @Autowired
    private RcareerService rcareerservice;
    
    @Autowired
    private RviewService rviewservice;
    
    @RequestMapping("/resume")
    public String resuem(HashMap<String, String> param, HttpSession session, Model model) {
    	String puserid = (String) session.getAttribute("id");
    	param.put("puserid", puserid); //나중에 찾아서 맞춰야함
    	
    	PusertbDTO dto = Pservice.PInfoView(param);
    	
    	ArrayList<ShowskilltbDTO> skilldto = showskillservice.selectAll();
    	
    	model.addAttribute("userlist", dto);
    	model.addAttribute("skilldto", skilldto);
    	
    	return "/resume/resume";
    }
    
    @PostMapping("/resumesave")
    public String resumesave(@RequestParam HashMap<String, String> param, @RequestParam("imgfile") MultipartFile uploadFile, Model model, HttpSession session) {
        log.info("# resumesave");
        log.info("# param=" + param);
        String puserid = (String) session.getAttribute("id");
        param.put("puserid", puserid);

        //일자 세팅
        String birdy = param.get("birdy").replace(".", "");
        //String sdate = param.get("sdate").replace(".", "");
        //String edate = param.get("edate").replace(".", "");
        
        param.put("birdy", birdy);
        //param.put("sdate", sdate+"01"); //날짜 형태를 위해 01 붙임
        //param.put("edate", edate+"01");  //날짜 형태를 위해 01 붙임
        
        service.resumesave(param); //이력서 저장
        int prono = service.getMaxProno(puserid); // 이력서 저장 후 저장된 이력서의 번호(prono)
        param.put("prono", prono+"");
        log.info(servletContext.getRealPath("/"));
        
        //선택한 스킬 세팅
        if(!param.get("skillno").equals("")) {
	        String[] skill = param.get("skillno").split(",");
	        param.put("prono", prono+"");
	        
	        for (int i = 0; i < skill.length; i++) {
	        	param.put("sequeno", skill[i]);
	        	skillservice.skillinsert(param);
			}
        }
        
        //입력한 경력 세팅
        if(!param.get("corpnm_s").equals("")) {
        	String[] corpnm = param.get("corpnm_s").split(",");
        	String[] sdate = param.get("sdate_s").split(",");
        	String[] edate = param.get("edate_s").split(",");
        	String[] wrkty = param.get("wrkty_s").split(",");
        	String[] position = param.get("position_s").split(",");
        	String[] task = param.get("task_s").split(",");
        	
        	for (int i = 0; i < corpnm.length; i++) {
				param.put("corpnm", corpnm[i]);
				param.put("sdate", sdate[i]);
				param.put("edate", edate[i]);
				param.put("wrkty", wrkty[i]);
				param.put("position", position[i]);
				param.put("task", task[i]);
				
				rcareerservice.addRcareer(param); // 경력 데이터 추가
			}
        }
        
        //이력서 저장 후 사진 존재 시 저장 
        if(!uploadFile.isEmpty()) {
        	UUID uuid = UUID.randomUUID(); //중복 방지 랜덤 난수 생성
        	String uploadFileName = uploadFile.getOriginalFilename(); //업로드되는 파일 이름
        	
        	log.info("업로드할 사진이 있음");
        	String basepath = servletContext.getRealPath("/"); //"D:\\dev\\projectupload";
    		String path = "resume\\"+puserid;//파일 경로 아이디 기준으로 사진 폴더 생성
    		
    		ImgtbDTO imgtb = new ImgtbDTO(); //이미지 정보 테이블
    		//테이블 세팅
    		imgtb.setUsetb("resumetb");
    		imgtb.setGubun(puserid+"_"+prono);
    		imgtb.setUuid(uuid.toString());
    		imgtb.setUploadpath(path);
    		imgtb.setFilename(uploadFileName);
    		imgservice.imgupload_resume(imgtb, uploadFile, basepath); //imgtb에 데이터 추가 및 해당 파일 저장
        }
        
        List<ResumetbDTO> resumeList = service.resumelist(param);
        model.addAttribute("resume2", resumeList);

        return "redirect:resumelist";
    }
    
	@RequestMapping("/resumelist") //jsp 액션 이름
	public String resumelist(@RequestParam HashMap<String, String> param, Criteria cri, Model model, HttpSession session) {
		log.info("@# resumelist");
		
		String puserid = (String) session.getAttribute("id");
		param.put("puserid", puserid);
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		ArrayList<ResumetbDTO> list = service.resumelist(param);
		model.addAttribute("resumelist", list);
		log.info("@# list => " + list);
		log.info("@# pageNum => " + cri.getPageNum());
		log.info("@# amount => " + cri.getAmount());
		//페이징 처리
		int total = service.getTotalCount(param); //전체 개수
		model.addAttribute("pageMaker", new PageDTO(total, cri));
		
		return "resume/resumelist";
	}
	
	@RequestMapping("/resumeselect") //jsp 액션이름
	public String resumeselect(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# resumeselect");
		
		String puserid = (String) session.getAttribute("id");
		param.put("puserid", puserid);
		log.info("@# prono => " + param.get("prono"));
		
		ArrayList<ShowskilltbDTO> showskilldto = showskillservice.selectAll();
		ArrayList<SkilltbDTO> skilldto = skillservice.select_resume(param);
		ArrayList<JobsorttbDTO> jobsort = jobsortservice.jobsortAll();
		ArrayList<RcareerDTO> rcareerList = rcareerservice.selectRcareer(param); // 경력 정보 가져오기
		
		ResumetbDTO dto = service.resumeselect(param);
		model.addAttribute("resumeselect", dto);
		model.addAttribute("jobsort", jobsort);//직무 출력
		model.addAttribute("showskilldto", showskilldto); //스킬 출력 목록
		model.addAttribute("skilldto", skilldto); //선택한 스킬 출력
		model.addAttribute("rcareerList", rcareerList); // 경력 정보 추가
		
		return "resume/resumeupdate";
	}
	
	@RequestMapping("/resumeupdate") //이력서 수정
	public String resumeupdate(@RequestParam HashMap<String, String> param, @RequestParam("imgfile") MultipartFile uploadFile, Model model, HttpSession session) {
		log.info("@# resumeupdate");
		log.info("@# param => " + param);
		String puserid = (String) session.getAttribute("id");
		param.put("puserid", puserid);
		
		log.info("@# prono => " + param.get("prono"));
		service.resumeupdate(param); //이력서 업데이트
		
		//선택한 스킬 세팅
        if(!param.get("skillno").equals("")) {
        	//기존의 스킬 삭제 후 다시 insert
        	skillservice.skilldelete(param); //기존 이력서에 등록된 스킬 삭제
        	
	        String[] skill = param.get("skillno").split(",");
	        
	        for (int i = 0; i < skill.length; i++) {
	        	param.put("sequeno", skill[i]);
	        	skillservice.skillinsert(param);
			}
        }
        
        //수정하는 경력 전체 삭제 후 다시 입력...
        rcareerservice.delete_career(param); //해당 이력서 기준 경력 전체 삭제
        if(!param.get("corpnm_s").equals("")) {
        	String[] corpnm = param.get("corpnm_s").split(",");
        	String[] sdate = param.get("sdate_s").split(",");
        	String[] edate = param.get("edate_s").split(",");
        	String[] wrkty = param.get("wrkty_s").split(",");
        	String[] position = param.get("position_s").split(",");
        	String[] task = param.get("task_s").split(",");
        	
        	for (int i = 0; i < corpnm.length; i++) {
				param.put("corpnm", corpnm[i]);
				param.put("sdate", sdate[i]);
				param.put("edate", edate[i]);
				param.put("wrkty", wrkty[i]);
				param.put("position", position[i]);
				param.put("task", task[i]);
				
				rcareerservice.addRcareer(param); // 경력 데이터 추가
			}
        }
        
        //이력서 저장 후 사진 존재 시 저장 
        if(!uploadFile.isEmpty()) {
        	//기존 이미지 파일 및 DB 삭제
        	ImgtbDTO imgtb = new ImgtbDTO();
        	imgtb.setImgno(Integer.parseInt(param.get("imgno")));
        	imgtb.setUsetb("resumetb");
    		imgtb.setGubun(puserid+"_"+param.get("prono"));
    		
    		int cnt = imgservice.imgcnt(imgtb);
    		if(cnt > 0)
    			imgservice.imgdelete_resume(imgtb); //이미지 파일 삭제 및 DB 삭제
        	
        	//이미지 파일 삭제 및 DB 삭제 후 다시 등록
        	UUID uuid = UUID.randomUUID(); //중복 방지 랜덤 난수 생성
        	String uploadFileName = uploadFile.getOriginalFilename(); //업로드되는 파일 이름
        	
        	log.info("업로드할 사진이 있음");
        	String basepath = servletContext.getRealPath("/"); //"D:\\dev\\projectupload";
    		String path = "resume\\"+puserid;//파일 경로 아이디 기준으로 사진 폴더 생성
    		
    		//ImgtbDTO imgtb = new ImgtbDTO(); //이미지 정보 테이블
    		imgtb = new ImgtbDTO(); //이미지 정보 테이블
    		//테이블 세팅
    		imgtb.setUsetb("resumetb");
    		imgtb.setGubun(puserid+"_"+param.get("prono"));
    		imgtb.setUuid(uuid.toString());
    		imgtb.setUploadpath(path);
    		imgtb.setFilename(uploadFileName);
    		imgservice.imgupload_resume(imgtb, uploadFile, basepath); //imgtb에 데이터 추가 및 해당 파일 저장
        }

        return "redirect:resumelist";
	}
	
	@GetMapping("/resumedisplay")
	public String resumedisplay(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# resumedisplay");
		String puserid = (String) session.getAttribute("id");
		param.put("puserid", puserid);
		
		ArrayList<SkilltbDTO> skill_list = skillservice.select_resume(param); // 이력서에 등록된 스킬 목록 조회
		ArrayList<RcareerDTO> rcareerList = rcareerservice.selectRcareer(param); // 경력 정보 가져오기
		
		ResumetbDTO dto = service.resume_view(param);
		model.addAttribute("resumeselect", dto);
		model.addAttribute("skill_list", skill_list);
		model.addAttribute("rcareerList", rcareerList); // 경력 정보 추가
		
		return "resume/resumetb_view";
	}
	
	@GetMapping("/deleteresume")
	public String deleteresume(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# deleteresume");
		log.info("@# param => "+param);
		String puserid = (String) session.getAttribute("id");
		param.put("puserid", puserid);
		
		log.info("@# imgno =>"+param.get("imgno"));
		if(!param.get("imgno").equals("0")) {
			ImgtbDTO imgdto = new ImgtbDTO();
			//Imgtb 기본키 세팅
			imgdto.setImgno(Integer.parseInt(param.get("imgno")));
			imgdto.setUsetb("resumetb");
			imgdto.setGubun(puserid+"_"+param.get("prono"));
			
			imgservice.imgdelete_resume(imgdto); //이미지 파일 및 이미지 DB 삭제
		}
		
		//선택한 기술 삭제
		skillservice.skilldelete(param);
		
		//이력서 삭제
		service.resumedelete(param);
		
		return "redirect:resumelist";
	}
	
	@PostMapping("/jobaplysupport")
    public String jobaplysupport(@RequestParam HashMap<String, String> param, HttpSession session, Model model) {
        log.info("@# jobaplysupport");
        log.info("@# param => " + param);
		String puserid = (String) session.getAttribute("id");
        
        param.put("puserid", puserid);
        param.put("cuserid", param.get("writer"));
        
        jobaplyservice.insertjobapply(param);
        jobaplyservice.incrementSupno(param);
        
        return "redirect:resumePopup";
    }
	
	@GetMapping("/resumePopup")
    public String resumePopup(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# resumePopup");
		log.info("@# param => "+param);
        String puserid = (String) session.getAttribute("id");

        ArrayList<ResumetbDTO> resumelist = service.getResumeList(puserid);
        model.addAttribute("resumelist", resumelist);
        model.addAttribute("writer", param.get("writer"));
        model.addAttribute("csrno", param.get("csrno"));
        model.addAttribute("jobno", param.get("jobno"));
        
        return "resume/resumePopup"; // 실제 JSP 파일 경로에 맞게 설정
    }
	
	@RequestMapping("return_list")
	public String return_list() {
		return "redirect:resumelist";
	}
	
	@PostMapping("/setRepresentative")
	@ResponseBody
	public Map<String, Object> setRepresentative(@RequestParam Long prono, @RequestParam Long imgno, HttpSession session) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        String puserid = (String) session.getAttribute("id");
	        service.setRepresentative(puserid, prono, imgno);
	        response.put("success", true);
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("success", false);
	        response.put("message", e.getMessage());
	    }
	    return response;
	}
	
	@RequestMapping("/resume_v")
	public String resume_v(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
	    log.info("@# resume_v");
	    
	    //model.addAttribute("list", list);
		ArrayList<SkilltbDTO> skill_list = skillservice.select_resume(param); // 이력서에 등록된 스킬 목록 조회
		ArrayList<RcareerDTO> rcareerList = rcareerservice.selectRcareer(param); // 경력 정보 가져오기
		
		ResumetbDTO dto = service.resume_view(param);
		model.addAttribute("resumeselect", dto);
		model.addAttribute("skill_list", skill_list);
		model.addAttribute("rcareerList", rcareerList); // 경력 정보 추가
		
		if(session.getAttribute("usergubun").equals("c")) {
			RviewtbDTO rdto = new RviewtbDTO();
			rdto.setPuserid(param.get("puserid"));
			rdto.setCuserid((String)session.getAttribute("id")); //기업 로그인 기준
			rdto.setProno(Integer.parseInt(param.get("prono")));
			rviewservice.c_rview(rdto);//이력서 열람 이력 추가
		}
		
		//return "resumesearch/resume_v";
		return "resume/resumetb_view";
	}
	
	@RequestMapping("/resumesearch")
	public String resumsearch(@RequestParam HashMap<String, String> param, Criteria cri, Model model) {
		log.info("@# resumsearch");
		log.info("@# cri =>"+cri);
		
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		ArrayList<ResumetbDTO> list = service.skillselect(param);
		int total = service.getCount(param);
		log.info("@# total=>"+total);

		//검색 조건 테이블 조회 쿼리
		ArrayList<ShowskilltbDTO> showskill = showskillservice.selectAll();//보유기술
		
		//검색 박스 값
		model.addAttribute("showskill", showskill);
		
		model.addAttribute("skilllist", list); //목록 리스트
		model.addAttribute("pageMaker", new PageDTO(total, cri)); //페이징
		
		return "resumesearch/resumesearch";
	}
	
	@RequestMapping("/skillSearch")
	public String skillSearch(@RequestParam HashMap<String, String> param, Criteria cri, Model model) {
		log.info("@# skillSearch");
	
		ArrayList<ResumetbDTO> list = service.skillselect(param);
		
		//jsp단의 hidden값에 세팅
		model.addAttribute("skill_s", param.get("skill_s"));
		
		model.addAttribute("skilllist", list);
		return "resumesearch/resumesearch";
	}
	
	@RequestMapping("/resumeSearchAjax")
	public ModelAndView resumeSearchAjax(@RequestParam HashMap<String, String> param, Criteria cri) { //, Model model) {
		log.info("@# resumeSearchAjax");
		ModelAndView mav = new ModelAndView();
		//mav.setViewName("jobpost_ajax");
		mav.setViewName("resumesearch/resumesearch_ajax");
		
		log.info("@# cri =>"+cri);
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		ArrayList<ResumetbDTO> list = service.skillselect(param);
		int total = service.getCount(param);
		log.info("@# total=>"+total);

		//jsp단의 hidden값에 세팅
		mav.addObject("loc_s", param.get("loc_s"));
		mav.addObject("skill_s", param.get("skill_s"));
		mav.addObject("pcareer_s", param.get("pcareer_s"));
		log.info("@# list => "+list);
		mav.addObject("skilllist", list);
		mav.addObject("pageMaker", new PageDTO(total, cri));
		
		return mav;
	}
	
	@GetMapping("resumesearchPageAjax")
	public ModelAndView resumesearchPageAjax(@RequestParam HashMap<String, String> param, Criteria cri) {
		log.info("@# resumesearchPageAjax");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("resumesearch/resumesearch_ajax");
		
		log.info("@# cri =>"+cri);
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		ArrayList<ResumetbDTO> list = service.skillselect(param);
		int total = service.getCount(param);
		log.info("@# total=>"+total);
		
		mav.addObject("loc_s", param.get("loc_s"));
		mav.addObject("skill_s", param.get("skill_s"));
		mav.addObject("pcareer_s", param.get("pcareer_s"));
		
		mav.addObject("skilllist", list);
		mav.addObject("pageMaker", new PageDTO(total, cri));
		return mav;
	}
	
	@RequestMapping("/resumesearchRestAjax")
	public ModelAndView resumesearchRestAjax(@RequestParam HashMap<String, String> param, Criteria cri) {
		log.info("@# resumesearchRestAjax");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("resumesearch/resumesearch_ajax");
		
		log.info("@# cri =>"+cri);
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		ArrayList<ResumetbDTO> list = service.skillselect(param);
		int total = service.getCount(param);
		log.info("@# total=>"+total);

		mav.addObject("skilllist", list);
		mav.addObject("pageMaker", new PageDTO(total, cri));
		return mav;
	}
	
	@PostMapping("/delete_resume")
	@ResponseBody
	public Map<String, Object> deleteResume(@RequestParam String prono, @RequestParam String imgno, HttpSession session) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        String puserid = (String) session.getAttribute("id");
	        HashMap<String, String> param = new HashMap<>();
	        param.put("prono", prono);
	        param.put("imgno", imgno);
	        param.put("puserid", puserid);

	        // 이미지 삭제
	        if (!imgno.equals("0")) {
	            ImgtbDTO imgdto = new ImgtbDTO();
	            imgdto.setImgno(Integer.parseInt(imgno));
	            imgdto.setUsetb("resumetb");
	            imgdto.setGubun(puserid + "_" + prono);
	            imgservice.imgdelete_resume(imgdto);
	        }

	        // 선택한 기술 삭제
	        skillservice.skilldelete(param);
	        
			//해당 이력서의 경력 삭제
			rcareerservice.delete_career(param);
	        
	        service.resumedelete(param);

	        response.put("success", true);
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("success", false);
	        response.put("message", e.getMessage());
	    }
	   	return response;
	}
}