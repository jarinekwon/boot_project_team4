package com.boot.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.ScraptbDTO;

@Mapper
public interface ScraptbDAO {
	public void scrap_p_insert(HashMap<String, String> param); //일반회원 스크랩 등록
	public void scrap_p_delete(HashMap<String, String> param); //일반회원 스크랩 삭제
	public ScraptbDTO scrap_p_select(HashMap<String, String> param); //일반회원 스크랩 확인 유무
	
	public List<ScraptbDTO> allselect_p(HashMap<String, String> param);
	public int allcount_p(HashMap<String, String> param);
	
	public void deleteAll_p(HashMap<String, String> param); //탈퇴하는 일반 계정 관련 삭제
	public void deleteAll_c(HashMap<String, String> param); //탈퇴하는 기업 계정 관련 삭제
}
