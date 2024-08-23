package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.CusertbDTO;
import com.boot.dto.PusertbDTO;
import com.boot.service.CusertbService;
import com.boot.service.PusertbService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@EnableAspectJAutoProxy
public class MyPageController {

    @Autowired
    private CusertbService Cservice;
    
    @Autowired
    private PusertbService Pservice;

    SessionController sessioncon = new SessionController();
    
    @RequestMapping("/mypage_ppwcheck")
    public String PPasswordcheck() {
        log.info("@# mypage_ppwcheck");

        return "mypage/mypage_ppwcheck";
    }

    @RequestMapping("/mypage_cpwcheck")
    public String Cpasswordcheck() {
        log.info("@# mypage_cpwcheck");

        return "mypage/mypage_cpwcheck";
    }

    @RequestMapping("/ppassword_yn")
    public String pcheck(@RequestParam HashMap<String, String> param, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        log.info("@# ppassword_yn");
        log.info("@#Received parameters: " + param);
        
        String puserid = (String) session.getAttribute("id");
        param.put("puserid", puserid);
        
        ArrayList<PusertbDTO> dtos = Pservice.PPasswordYn(param);
        model.addAttribute("content_pinfo", dtos);

        if (dtos.isEmpty()) {
        	redirectAttributes.addFlashAttribute("message", "비밀번호가 틀렸습니다.");
            return "redirect:mypage_ppwcheck";
        } else {
            log.info("@#Found matching records.");
            
            if (param.get("ppass").equals(dtos.get(0).getPpass())) {
            	
                return "redirect:content_pinfo";
            } else {
                log.info("@#Password mismatchXXX.");
                
                model.addAttribute("errorMessage", "비밀번호가 틀렸습니다.");
                redirectAttributes.addFlashAttribute("message", "비밀번호가 틀렸습니다.");
                return "redirect:mypage_ppwcheck";
            }
        }
    }

    @RequestMapping("/cpassword_yn")
    public String ccheck(@RequestParam HashMap<String, String> param, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        log.info("@# cpassword_yn");
        
        String cuserid = (String) session.getAttribute("id");
        param.put("cuserid", cuserid);
        
        ArrayList<CusertbDTO> dtos = Cservice.CPasswordYn(param);

        log.info("@# dtos =>"+dtos);
        if (dtos.isEmpty()) {
        	redirectAttributes.addFlashAttribute("message", "비밀번호가 틀렸습니다.");
            return "redirect:mypage_cpwcheck";
        } else {
            if (param.get("cpass").equals(dtos.get(0).getCpass())) {
                return "redirect:content_cinfo";
            } else {
            	redirectAttributes.addFlashAttribute("message", "비밀번호가 틀렸습니다.");
                return "redirect:mypage_cpwcheck";
            }
        }
    }

    @RequestMapping("/content_pinfo")
    public String PInfoView(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
        log.info("@# content_pinfo");

        String puserid = (String) session.getAttribute("id");
        if (puserid == null) {
            return "redirect:mypage_ppwcheck";
        }
        
        param.put("puserid", puserid);

        PusertbDTO dto = Pservice.PInfoView(param);
        log.info("@# controller param => "+param);
        
        model.addAttribute("content_pinfo", dto);

        return "mypage/content_pinfo";
    }

    @RequestMapping("/content_cinfo")
    public String CInfoView(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
        log.info("@# content_cinfo");

        String cuserid = (String) session.getAttribute("id");
        if (cuserid == null) {
            return "redirect:mypage_cpwcheck";
        }
        param.put("cuserid", cuserid);
        
        CusertbDTO dto = Cservice.CInfoView(param);
        model.addAttribute("content_cinfo", dto);

        return "mypage/content_cinfo";
    }

    @RequestMapping("/update_pinfo")
    public String PModify(@RequestParam HashMap<String, String> param) {
        log.info("@# PModify");
        log.info("@# PModify param => "+param);

        Pservice.PModify(param);
        return "redirect:main";
    }

    @RequestMapping("/update_cinfo")
    public String modify(@RequestParam HashMap<String, String> param) {
        log.info("@# CModify");

        Cservice.CModify(param);
        return "redirect:main";
    }
    
    @RequestMapping("/test")
    public String test() {
    	return "test";
    }
}
