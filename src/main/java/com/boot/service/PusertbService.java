package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.dto.PusertbDTO;

public interface PusertbService {
	public void pRegisterInsert(HashMap<String, String> param);
	public ArrayList<PusertbDTO> ploginYn(HashMap<String, String> param);
	public int idchk(String puserid);
	
	public ArrayList<PusertbDTO> PPasswordYn(HashMap<String, String> param);
	public PusertbDTO PInfoView(HashMap<String, String> param);
	public void PModify(HashMap<String, String> param);
	public void PDelete(HashMap<String, String> param);
	
	public ArrayList<PusertbDTO> pidfindYn(HashMap<String, String> param);// 일반 아이디찾기
	public void pupdatePassword(HashMap<String, String> param);// 일반 비밀번호 변경하기
}
