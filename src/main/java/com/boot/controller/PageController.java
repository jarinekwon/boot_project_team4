package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boot.dto.BoardtbDTO;
import com.boot.dto.CommenttbDTO;
import com.boot.dto.Criteria;
import com.boot.dto.PageDTO;
import com.boot.service.BoardService;
import com.boot.service.CommentService;
import com.boot.service.PageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PageController {
	@Autowired
	private PageService service;
	
	@Autowired
	private BoardService boardservice;
	
	@Autowired
	private CommentService commentService;
	
//	@RequestMapping("/listWithPaging")
	@RequestMapping("/list")
	public String listWithPaging(@RequestParam HashMap<String, String> param, Criteria cri, Model model) {
		log.info("@# list");
		log.info("@# param=>"+param);
		log.info("@# mypost => " + param.get("mypost"));

		ArrayList<BoardtbDTO> list = null;
		String sortOrder = param.get("sortOrder");
		log.info("sortOrder: " + sortOrder);
        if (sortOrder != null) {
            switch (sortOrder) {
                case "views_desc":
                    cri.setSort("views");
                    cri.setOrder("DESC");
                    break;
                case "views_asc":
                    cri.setSort("views");
                    cri.setOrder("ASC");
                    break;
                case "date_desc":
                    cri.setSort("date");
                    cri.setOrder("DESC");
                    break;
                case "date_asc":
                    cri.setSort("date");
                    cri.setOrder("ASC");
                    break;
            }
        } else {
            cri.setSort("date");
            cri.setOrder("DESC");
        }
		
        cri.setType(param.get("type"));
		cri.setKeyword(param.get("keyword"));
        
		String mypost = param.get("mypost");
		log.info("@# mypost =>" +  mypost);
		if(mypost == null || !mypost.equals("my")) {
			list = service.listWithPaging(cri); //일반 검색
		} else {
			list = service.listWithPaging_writer(cri); //작성자 기준 검색
			
		}
//		int total = service.getTotalCount();
		int total = service.getTotalCount(cri);
		log.info("@# total=>"+total);
		
		model.addAttribute("list", list);
//		model.addAttribute("pageMaker", new PageDTO(123, cri));
		model.addAttribute("pageMaker", new PageDTO(total, cri));
		model.addAttribute("sortOrder", sortOrder);
		
		return "board/list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# content_view");
		
		boardservice.upHit(param);
		BoardtbDTO dto = boardservice.contentView(param);
		model.addAttribute("content_view", dto);
		
		
//		content_view.jsp 에서 pageMaker 를 가지고 페이징 처리
		model.addAttribute("pageMaker", param);
		
		// 해당 게시글에 작성된 댓글 리스트를 가져옴
		ArrayList<CommenttbDTO> commentList = commentService.findAll(param);
		model.addAttribute("commentList", commentList);
		model.addAttribute("userid", (String)session.getAttribute("id"));
		
		return "board/content_view";
	}
	
	@PostMapping("comment/AjaxShow")
	public ModelAndView AjaxShow(@RequestParam HashMap<String, String> param) {
		log.info("@# Ajaxshow");
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("board/comment_ajax");
		
		log.info("@# boardno => " + param.get("boardno"));
		
		// 해당 게시글에 작성된 댓글 리스트를 가져옴
		ArrayList<CommenttbDTO> commentList = commentService.findAll(param);
		
		mav.addObject("commentList", commentList);
		
		return mav;
	}
}