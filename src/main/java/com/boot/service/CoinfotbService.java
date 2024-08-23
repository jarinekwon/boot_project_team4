package com.boot.service;

import java.util.HashMap;

import com.boot.dto.CoinfotbDTO;

public interface CoinfotbService {
	public void insert(HashMap<String, String> param);
	public CoinfotbDTO Coinfotblist(HashMap<String, String> param);
	public CoinfotbDTO Coinfotbinfo(HashMap<String, String> param); //공고 화면에 정보 출력
	public void Coinmodify(HashMap<String, String> param); //기업 정보 수정
	public void delete(HashMap<String, String> param); //탈퇴 기업 회원 정보 삭제
}
