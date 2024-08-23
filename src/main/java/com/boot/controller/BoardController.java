package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.BoardtbDTO;
import com.boot.dto.CommenttbDTO;
import com.boot.service.BoardService;
import com.boot.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	@Autowired
	private BoardService service;
	
	@Autowired
	private CommentService commentService;

	//@Autowired
	//private UploadService uploadService;
	
//	@RequestMapping("/list")
	/*
	@RequestMapping("/list_old")
	public String list(Model model) {
		log.info("@# list");
		
		ArrayList<BoardtbDTO> list = service.list();
		model.addAttribute("list", list);
		
		return "list";
	}
	*/
	@RequestMapping("/write")
	public String write(BoardtbDTO boardtbDTO, HttpSession session) {
		log.info("@# write");
		log.info("@# boardDTO=>"+boardtbDTO);
		
		String authorid = (String) session.getAttribute("id");
		boardtbDTO.setAuthorid(authorid);
		
		service.write(boardtbDTO);
		
		return "redirect:list";
	}
	
	@RequestMapping("/write_view")
	public String write_view() {
		log.info("@# write_view");
		
		return "board/write_view";
	}
	
	@RequestMapping("/modify_view")
	public String modify_view(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# modify_view");

		BoardtbDTO dto = service.modify_view(param);
		model.addAttribute("modify_view", dto);

		return "board/modify_view";
	}
	
	@RequestMapping("/modify")
//	public String modify(@RequestParam HashMap<String, String> param) {
	public String modify(@RequestParam HashMap<String, String> param, RedirectAttributes rttr) {
		log.info("@# modify");
		log.info("@# param=>"+param);
		
		service.modify(param);
		
//		페이지 이동시 뒤에 페이지번호, 글 갯수 추가
		rttr.addAttribute("pageNum", param.get("pageNum"));
		rttr.addAttribute("amount", param.get("amount"));

		return "redirect:list";
	}
	
	@RequestMapping("/delete")
//	public String delete(@RequestParam HashMap<String, String> param) {
	public String delete(@RequestParam HashMap<String, String> param, RedirectAttributes rttr) {
		log.info("@# delete");
		log.info("@# param=>"+param);
		log.info("@# boardno=>"+param.get("boardno"));
		
		//List<BoardAttachDTO> fileList = uploadService.getFileList(Integer.parseInt(param.get("boardno")));
		
		service.delete(param);
		//uploadService.deleteFiles(fileList);
		
//		페이지 이동시 뒤에 페이지번호, 글 갯수 추가
		rttr.addAttribute("pageNum", param.get("pageNum"));
		rttr.addAttribute("amount", param.get("amount"));
		
		return "redirect:list";
	}
}