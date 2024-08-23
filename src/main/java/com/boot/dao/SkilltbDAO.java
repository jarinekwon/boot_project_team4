package com.boot.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.SkilltbDTO;

@Mapper
public interface SkilltbDAO {
	public void skillinsert(HashMap<String, String> param);
	public ArrayList<SkilltbDTO> select_resume(HashMap<String, String> param); //이력서 삭제
	public void skilldelete(HashMap<String, String> param); //이력서 삭제 시, 관련된 스킬 삭제 
}