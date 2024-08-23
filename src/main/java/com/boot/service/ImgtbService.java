package com.boot.service;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.boot.dto.ImgtbDTO;

public interface ImgtbService {
	public void imgupload(ImgtbDTO imgtbdto);
	public ImgtbDTO getFileselect(HashMap<String, String> param);
	public ImgtbDTO getFile_coinfo_select(HashMap<String, String> param);
	
	public void imgupload_resume(ImgtbDTO imgtbdto, MultipartFile uploadFile, String basepath); //이미지 파일 추가 및 DB 등록
	public void imgdelete_resume(ImgtbDTO imgtbdto); //이미지 파일 삭제 및 DB 삭제
	
	public void imgdelete_coifno(ImgtbDTO imgtbdto); //이미지 파일 삭제 및 DB 삭제(조건이 달라서 새로 만듬)
	public int imgcnt(ImgtbDTO imgtbdto);//이미지 여부 확인
}
