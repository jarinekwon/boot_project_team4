package com.boot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.boot.dto.ImgtbDTO;
import com.boot.service.ImgtbService;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;


@Slf4j
@Controller
public class ImgController {
	
	@Autowired
	private ImgtbService service;
	
	@Autowired
    private ServletContext servletContext; // ServletContext 주입

	//이미지 파일을 받아서 화면에 출력(byte 배열타입)
	@GetMapping("/display_img_show") //개인이 쓴 이력서 목록에서 보이는 사진
	public ResponseEntity<byte[]> getFiles(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# getFiles");
		//폴더에 저장된 파일을 화면에 출력
		String puserid = (String) session.getAttribute("id");
		
		param.put("puserid", puserid);
		param.put("usetb", "resumetb");
		param.put("gubun", puserid+"_"+param.get("prono"));
		
		if(!param.get("imgno").equals("0")) {
			ImgtbDTO dto = service.getFileselect(param);
			log.info("@# dto => "+dto);
			//업로드 파일경로+이름
			File file = new File(servletContext.getRealPath("/")+dto.getUploadpath()+"/s_"+dto.getUuid()+"_"+dto.getFilename());
			log.info("@# file=> "+file);
			
			ResponseEntity<byte[]> result = null;
			HttpHeaders headers = new HttpHeaders();
			try {
				//파일타입을 헤더에 추가
				headers.add("Content-Type", Files.probeContentType(file.toPath()));
				//파일정보를 byte 배열로 복사+헤더정보+http상태 정상을 결과에 저장
				result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
			}
			return result; //결과를 jsp에 반환
		} else {
			return null;
		}
	}
	
	//이미지 파일을 받아서 화면에 출력(byte 배열타입)
	@GetMapping("/show_resume_img") //이력서 관련 사진 출력
	public ResponseEntity<byte[]> show_s_img(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# getImg_jobplylist");
		//폴더에 저장된 파일을 화면에 출력
		param.put("usetb", "resumetb");
		param.put("gubun", param.get("writer")+"_"+param.get("prono"));
		File file = null;
		if(!param.get("imgno").equals("0")) {
			ImgtbDTO dto = service.getFileselect(param);
			log.info("@# dto => "+dto);
			//업로드 파일경로+이름
			log.info("@# imggubun => " + param.get("imggubun"));
			if(param.get("imggubun").equals("s")) {
				file = new File(servletContext.getRealPath("/")+dto.getUploadpath()+"/s_"+dto.getUuid()+"_"+dto.getFilename());
			} else {
				file = new File(servletContext.getRealPath("/")+dto.getUploadpath()+"/"+dto.getUuid()+"_"+dto.getFilename());
			}
			log.info("@# file=> "+file);
			
			ResponseEntity<byte[]> result = null;
			HttpHeaders headers = new HttpHeaders();
			try {
				//파일타입을 헤더에 추가
				headers.add("Content-Type", Files.probeContentType(file.toPath()));
				//파일정보를 byte 배열로 복사+헤더정보+http상태 정상을 결과에 저장
				result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
			}
			return result; //결과를 jsp에 반환
		} else {
			return null;
		}
	}
	
	//이미지 파일을 받아서 화면에 출력(byte 배열타입)
	@GetMapping("/show_coinfo_img") //기업 관련 사진 출력
	public ResponseEntity<byte[]> show_coinfo_img(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# show_coinfo_img");
		log.info("@# param => " + param);
		//폴더에 저장된 파일을 화면에 출력
		param.put("usetb", "coinfotb");
		param.put("gubun", param.get("writer")+"_"+1);
		File file = null;
		
		if(!param.get("imgno").equals("n")) {
			ImgtbDTO dto = service.getFile_coinfo_select(param);
			log.info("@# dto => "+dto);
			//업로드 파일경로+이름
			log.info("@# imggubun => " + param.get("imggubun"));
			if(param.get("imggubun").equals("s")) {
				file = new File(servletContext.getRealPath("/")+dto.getUploadpath()+"/s_"+dto.getUuid()+"_"+dto.getFilename());
			} else {
				file = new File(servletContext.getRealPath("/")+dto.getUploadpath()+"/"+dto.getUuid()+"_"+dto.getFilename());
			}
			log.info("@# file=> "+file);
			
			ResponseEntity<byte[]> result = null;
			HttpHeaders headers = new HttpHeaders();
			try {
				//파일타입을 헤더에 추가
				headers.add("Content-Type", Files.probeContentType(file.toPath()));
				//파일정보를 byte 배열로 복사+헤더정보+http상태 정상을 결과에 저장
				result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
			}
			return result; //결과를 jsp에 반환
		} else {
			return null;
		}
	}
}