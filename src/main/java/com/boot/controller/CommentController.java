package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.boot.dto.CommenttbDTO;
import com.boot.service.CommentService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("/comment")
public class CommentController {
	//서비스단
	@Autowired
	private CommentService service;
	
	@PostMapping("/save")
//	public ArrayList<CommentDTO> save(@RequestParam HashMap<String, String> param) {
	public @ResponseBody ArrayList<CommenttbDTO> save(@RequestParam HashMap<String, String> param) {
		log.info("@# save");
		log.info("@# param=>"+param);
		
		service.save(param);
		
		// 해당 게시글에 작성된 댓글 리스트를 가져옴
		ArrayList<CommenttbDTO> commentList = service.findAll(param);
		
		return commentList;
	}
	
	@PostMapping("/Ajaxsave")
	public ModelAndView ajaxsave(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# ajaxsave");
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("board/comment_ajax");
		log.info("@# boardno => "+param.get("boardno"));
		
		param.put("replyid", (String) session.getAttribute("id"));
		
		service.save(param); // 댓글 저장
		
		// 해당 게시글에 작성된 댓글 리스트를 가져옴
		ArrayList<CommenttbDTO> commentList = service.findAll(param);
		
		mav.addObject("commentList", commentList);
		return mav;
	}
	
	@PostMapping("/modify")
    public ModelAndView modify(@RequestParam HashMap<String, String> param) {
        log.info("@# modify");
        ModelAndView mav = new ModelAndView();
        
        mav.setViewName("board/comment_ajax");
        log.info("@# boardno => " + param.get("boardno"));
        
        service.modify(param);
        
        // 해당 게시글에 작성된 댓글 리스트를 가져옴
        ArrayList<CommenttbDTO> commentList = service.findAll(param);
        
        mav.addObject("commentList", commentList);
        return mav;
    }
    
    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam HashMap<String, String> param) {
        log.info("@# delete");
        ModelAndView mav = new ModelAndView();
        
        mav.setViewName("board/comment_ajax");
        log.info("@# boardno => " + param.get("boardno"));
        
        service.delete(param);
        
        // 해당 게시글에 작성된 댓글 리스트를 가져옴
        ArrayList<CommenttbDTO> commentList = service.findAll(param);
        
        mav.addObject("commentList", commentList);
        return mav;
    }
}