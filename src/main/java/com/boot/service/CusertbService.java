package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.dto.CusertbDTO;

public interface CusertbService {
	public void cRegisterInsert(HashMap<String, String> param);
	public ArrayList<CusertbDTO> cloginYn(HashMap<String, String> param);
	public int idchk(String puserid);
	
	public ArrayList<CusertbDTO> CPasswordYn(HashMap<String, String> param);
	public CusertbDTO CInfoView(HashMap<String, String> param);
	public void CModify(HashMap<String, String> param);
	public void CDelete(HashMap<String, String> param);
	
	public ArrayList<CusertbDTO> cidfindYn(HashMap<String, String> param);// 기업 아이디찾기
	public void cupdatePassword(HashMap<String, String> param);// 기업 비밀번호 변경하기
}
