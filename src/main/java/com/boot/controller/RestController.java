package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.BoardtbDTO;
import com.boot.dto.ImgtbDTO;
import com.boot.dto.JobaplytbDTO;
import com.boot.dto.PusertbDTO;
import com.boot.dto.ResumetbDTO;
import com.boot.service.BoardService;
import com.boot.service.CallCenterService;
import com.boot.service.CoinfotbService;
import com.boot.service.CommentService;
import com.boot.service.CusertbService;
import com.boot.service.ImgtbService;
import com.boot.service.JobaplyService;
import com.boot.service.JobposttbService;
import com.boot.service.PusertbService;
import com.boot.service.RcareerService;
import com.boot.service.ResumeService;
import com.boot.service.ScribeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private PusertbService pservice;
	
	@Autowired
	private CusertbService cservice;
	
	@Autowired
	private ResumeService resumeservice;
	
	@Autowired
	private JobaplyService jobaplyservice;
	
	@Autowired
	private ImgtbService imgtbservice;
	
	@Autowired
	private JobposttbService jobpostservice;
	
	@Autowired
	private CoinfotbService coinfoservice;
	
	@Autowired
	private ScribeService scribeservice;
	
	@Autowired
	private CallCenterService callservice;
	
	@Autowired
	private BoardService boardservice;
	
	@Autowired
	private CommentService commentservice;
	
	@Autowired
	private RcareerService rcareerservice;
	
	@PostMapping("/RestUserInsert_p")
	public ResponseEntity<String> RestRegisterInsert_p(@RequestParam HashMap<String, String> param) {
		log.info("@# RestRegisterInsert_p");
		log.info("@# param => "+ param);
		
		pservice.pRegisterInsert(param);

		return ResponseEntity.ok("success");
	}
	
	@PutMapping("/RestUserUpdate_p")
	public ResponseEntity<String> RestRegisterUpdate_p(@RequestParam HashMap<String, String> param) {
		log.info("@# RestRegisterUpdate_p");
        log.info("@# controller param => "+param);
        
		pservice.PModify(param);
		
		return ResponseEntity.ok("success");
	}
	
	@DeleteMapping("RestUserDelete_p")
	public ResponseEntity<String> RestRegisterDelete_p(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# RestRegisterDelete_p");
        log.info("@# controller param => "+param);
        param.put("authorid", param.get("puserid"));
        param.put("replyid", param.get("puserid"));
        
        //param.puserid => puserid
        //이력서 전체 검색(계정 기준)
        int cnt = resumeservice.resumeAll(param).size();
        ArrayList<ResumetbDTO> resumelist = resumeservice.resumeAll(param);
        //이력서에 등록한 사진 파일 삭제
        for (int i = 0; i < cnt; i++) {
        	//등록한 이미지 삭제
        	ImgtbDTO img = new ImgtbDTO();
        	img.setUsetb("resumetb");
        	img.setGubun(param.get("puserid")+"_"+resumelist.get(i).getProno());
        	
        	imgtbservice.imgdelete_coifno(img); //해당 이력서 관련 사진 삭제, 해당 메소드는 imgno 키가 필요 없음
		}
		
        cnt = jobaplyservice.jobaply_p_selectAll(param).size();
        ArrayList<JobaplytbDTO> jobaplylist = jobaplyservice.jobaply_p_selectAll(param);
        //지원한 이력 현황 삭제
        for (int i = 0; i < cnt; i++) {
        	param.put("cuserid", jobaplylist.get(i).getCuserid());
			param.put("csrno", jobaplylist.get(i).getCsrno()+"");
			param.put("jobno", jobaplylist.get(i).getJobno()+"");
			
			//jobposttb 에서 supno - 1
			jobpostservice.decreaseSupno(param); //탈퇴 회원으로 인한 지원자 수 감소
		}
        
		jobaplyservice.jobaply_p_all_delete(param); //지원 이력 내역 전체 삭제
        resumeservice.resumeAllDelete(param); //작성한 이력서 전체 삭제
        scribeservice.deleteAll_p(param); //스크랩 관련 삭제 (authorid)
        callservice.call_p_deleteAll(param);//문의 내역 전체 삭제 (authorid)
        
        cnt = boardservice.select_boardno(param).size();
        ArrayList<BoardtbDTO> boardlist = boardservice.select_boardno(param);
        for (int i = 0; i < cnt; i++) {
        	param.put("bordno", boardlist.get(i).getBoardno()+"");
        	commentservice.delete_p_All(param);//댓글 삭제 boardno를 이용하여 삭제
		}
        boardservice.delete_p_All(param); //게시판 삭제 (authorid)
        
        rcareerservice.delete_career_All(param);//경력 사항 삭제
        
        pservice.PDelete(param); //회원 정보 삭제
        
		//로그인 상태에서 탈퇴 진행 -> 세션 삭제
		session.invalidate();
		
		return ResponseEntity.ok("success");
	}
	
	@GetMapping("RestUserSelect_p")
	public ResponseEntity<ArrayList<PusertbDTO>> RestRegisterSelect_p(@RequestParam HashMap<String, String> param) {
		ResponseEntity<ArrayList<PusertbDTO>> result = null;
		ArrayList<PusertbDTO> list = pservice.PInfoAll(param);
		
		result = ResponseEntity.status(HttpStatus.OK).body(list);
		
		return result;
	}
	
	@PostMapping("/RestUserInsert_c")
	public ResponseEntity<String> RestRegisterInsert_c(@RequestParam HashMap<String, String> param) {
		log.info("@# RestRegisterInsert_c");
		log.info("@# param => "+ param);
		
		cservice.cRegisterInsert(param);

		return ResponseEntity.ok("success");
	}
	
	@PutMapping("/RestUserUpdate_c")
	public ResponseEntity<String> RestRegisterUpdate_c(@RequestParam HashMap<String, String> param) {
		log.info("@# RestRegisterUpdate_c");
        log.info("@# controller param => "+param);
        
		cservice.CModify(param);
		
		return ResponseEntity.ok("success");
	}
	
	@DeleteMapping("RestUserDelete_c")
	public ResponseEntity<String> RestRegisterDelete_c(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# RestRegisterDelete_c");
        log.info("@# controller param => "+param);
        param.put("scribeid", param.get("cuserid"));
        param.put("authorid", param.get("cuserid"));
        int cnt = 0;
        
        //이미지 파일 삭제 //회사 정보에 존재하는 이미지 삭제
        ImgtbDTO img = new ImgtbDTO();
        img.setUsetb("coinfotb");
        img.setGubun(param.get("cuserid")+"_1");
        imgtbservice.imgdelete_coifno(img);
        
        jobpostservice.deleteAll_c(param); //공고 이력 삭제
        //지원 이력 현황 삭제??
        coinfoservice.delete(param); //회사 정보 삭제
        scribeservice.deleteAll_c(param); //스크랩 테이블에서 삭제(scribeid)
        cnt = boardservice.select_boardno(param).size();
        ArrayList<BoardtbDTO> boardlist = boardservice.select_boardno(param);
        for (int i = 0; i < cnt; i++) {
        	param.put("bordno", boardlist.get(i).getBoardno()+"");
        	commentservice.delete_p_All(param);//댓글 삭제 boardno를 이용하여 삭제
		}
        boardservice.delete_p_All(param); //게시판 삭제 (authorid)
        
		cservice.CDelete(param); //계정 삭제
		
		//로그인 후 회원 탈퇴 진행, 세션 삭제
		session.invalidate();
		
		return ResponseEntity.ok("success");
	} 
}