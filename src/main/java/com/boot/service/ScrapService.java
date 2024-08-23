package com.boot.service;

import java.util.HashMap;
import java.util.List;

import com.boot.dto.ScraptbDTO;

public interface ScrapService {
	/* 스크랩 관련 영역 */
	public void scrap_p_insert(HashMap<String, String> param);
	public void scrap_p_delete(HashMap<String, String> param);
	public ScraptbDTO scrap_p_select(HashMap<String, String> param);
	/* 스크랩 관련 영역*/
	
	public List<ScraptbDTO> allselect_p(HashMap<String, String> param);
	public int allcount_p(HashMap<String, String> param);
	
	public void deleteAll_p(HashMap<String, String> param); //탈퇴하는 일반 계정 관련 삭제
	public void deleteAll_c(HashMap<String, String> param); //탈퇴하는 기업 계정 관련 삭제
}
