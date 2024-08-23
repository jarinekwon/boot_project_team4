package com.boot.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.dto.Criteria;
import com.boot.dto.PageDTO;
import com.boot.dto.RviewtbDTO;
import com.boot.service.RviewService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
public class RviewtbController {
	@Autowired
	private RviewService rviewService;
	
	@RequestMapping("/rview")
	public String list(@RequestParam HashMap<String, String> param, Model model, HttpSession session, Criteria cri) {
		log.info("@# RviewController list");
		
		// 세션에 일반회원 id를 가져와서 파라미터에 추가
		String puserid = (String) session.getAttribute("id");
		param.put("puserid", puserid);
		
		// 페이지 정보 설정
		param.put("pageNum", cri.getPageNum() + "");
		param.put("amount", cri.getAmount() + "");
		
		// 총 데이터 수
		int total = rviewService.getTotalCount(param);
		
		// rview 리스트
		List<RviewtbDTO> rviewList = rviewService.rviewlist(param);
		
		// 모델에 데이터 추가
		model.addAttribute("rviewList", rviewList);
		model.addAttribute("pageMaker", new PageDTO(total, cri));
		
		return "rview/rview";
	}
}
