package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.dto.SkilltbDTO;

public interface SkilltbService {
	public void skillinsert(HashMap<String, String> param);
	public ArrayList<SkilltbDTO> select_resume(HashMap<String, String> param); //이력서 삭제
	public void skilldelete(HashMap<String, String> param); //선택한 스킬 삭제
}
