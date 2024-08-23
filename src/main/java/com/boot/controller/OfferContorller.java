package com.boot.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.Criteria;
import com.boot.dto.PageDTO;
import com.boot.service.OfferService;
import com.boot.service.PusertbService;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@Slf4j
public class OfferContorller {
	
	@Autowired
	private OfferService service;
	
	@Autowired
	private PusertbService puserservice;
	
	/* sms 전송을 위한 세팅 */
	final DefaultMessageService messageService;
	
	public OfferContorller() {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.messageService = NurigoApp.INSTANCE.initialize("NCSEPSQXUWDO2WGS", "BJUJJHURG1BIKIUKNKJLH1XIIQWPIYSL", "https://api.coolsms.co.kr");
    }
    /* sms 전송을 위한 세팅 */
	
	@RequestMapping("offerview")
	public String offerview(@RequestParam HashMap<String, String> param, Model model, HttpSession session, Criteria cri) {
		log.info("@# offerview");
		param.put("puserid", (String)session.getAttribute("id"));
		
		// 페이지 정보 설정
		param.put("pageNum", cri.getPageNum() + "");
		param.put("amount", cri.getAmount() + "");
		
		// 총 데이터 수
		int total = service.getTotalCount(param);
		
		model.addAttribute("offerlist", service.offerlist(param));
		model.addAttribute("pageMaker", new PageDTO(total, cri));
		return "offer/offerview";
	}
	
	@RequestMapping("offerPopup")
	public String offerPopup(@RequestParam HashMap<String, String> param, Model model) {
		log.info("offerPopup");
		
		Date today = new Date();
		
		model.addAttribute("data", param);
		model.addAttribute("date", today);
		return "offer/offerPopup";
	}
	
	@RequestMapping("offerViewPopup")
	public String offerViewPopup(@RequestParam HashMap<String, String> param, Model model) {
		log.info("offerViewPopup");
		
		model.addAttribute("data", service.offerValue(param));
		model.addAttribute("gubun", param.get("gubun"));
		return "offer/offerViewPopup";
	}
	
	@RequestMapping("offersave")
	public ResponseEntity<String> offersave(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# offersave");
		
		param.put("cuserid", (String)session.getAttribute("id"));
		service.offersave(param);
		
		String phone = puserservice.puserphone(param);
		
		/* 문자 전송을 위한 세팅 (금액 문제로 현재는 주석 처리) */
		/*
		Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01049190758");
        message.setTo(phone);
        message.setText("포지션 제안이 도착했습니다. 로그인 후 확인 가능합니다.");
		
        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        */
        /* 문자 전송을 위한 세팅 */
		
		return ResponseEntity.ok("success");
	}
	
	@RequestMapping("offeryn")
	public ResponseEntity<Object> offeryn(@RequestParam HashMap<String, String> param) {
		log.info("@# offeryn");
		log.info("@# param => " + param);
		String message = "";
		String gubun = param.get("gubun");
		if(param.get("gubun").equals("Y")) {
			service.offerreply(param);
			message = "해당 제안을 수락했습니다.";
		} else {
			service.offerreply(param);
			message = "해당 제안을 거절했습니다.";
		}
		
		return ResponseEntity.ok().body("{\"message\": \""+message+"\", \"gubun\": \""+gubun+"\"}");
	}
	
	@RequestMapping("offercview")
	public String requestMethodName(@RequestParam HashMap<String, String> param, HttpSession session, Model model, Criteria cri) {
		log.info("@# offercview");
		param.put("cuserid", (String)session.getAttribute("id"));
		
		// 페이지 정보 설정
		param.put("pageNum", cri.getPageNum() + "");
		param.put("amount", cri.getAmount() + "");
		
		// 총 데이터 수
		int total = service.getTotalCount_c(param);
		
		model.addAttribute("offerlist", service.offerlist_c(param));
		model.addAttribute("pageMaker", new PageDTO(total, cri));
		
		return "offer/offercview";
	}
}