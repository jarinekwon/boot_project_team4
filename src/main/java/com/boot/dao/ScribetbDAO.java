package com.boot.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.ScribetbDTO;

@Mapper
public interface ScribetbDAO {
	public void scribe_p_insert(HashMap<String, String> param); //일반회원 스크랩 등록
	public void scribe_p_delete(HashMap<String, String> param); //일반회원 스크랩 삭제
	public ScribetbDTO scribe_p_select(HashMap<String, String> param); //일반회원 스크랩 확인 유무
	
	public List<ScribetbDTO> allselect_p(HashMap<String, String> param);
	public int allcount_p(HashMap<String, String> param);
	
	public void deleteAll_p(HashMap<String, String> param); //탈퇴하는 일반 계정 관련 삭제
	public void deleteAll_c(HashMap<String, String> param); //탈퇴하는 기업 계정 관련 삭제
}
