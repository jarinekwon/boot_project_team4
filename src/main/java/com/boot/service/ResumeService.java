package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.dto.ResumetbDTO;

public interface ResumeService {
	public void resumesave(HashMap<String, String> param); //이력서 저장
	public ArrayList<ResumetbDTO> resumelist(HashMap<String, String> param); //이력서 목록 출력
	public ResumetbDTO resumeselect(HashMap<String, String> param); //선택한 이력서 출력
	public int getMaxProno(String puserid); //저장 후 이력서 번호 최댓값 가져오기
	public void resumedelete(HashMap<String, String> param); //선택한 이력서 삭제
	public ResumetbDTO resume_view(HashMap<String, String> param); //이력서 뷰어에서 출력
	public ArrayList<ResumetbDTO> getResumeList(String puserid); //공고 지원 팝업창에 출력할 이력서 목록
	public int getTotalCount(HashMap<String, String> param); //이력서 갯수
	public void resumeupdate(HashMap<String, String> param); // 이력서 수정
	public void setRepresentative(String puserid, Long prono, Long imgno); // 대표 이력서 설정
	
	public ResumetbDTO resume_v(HashMap<String, String> param);
	public ArrayList<ResumetbDTO> skillselect(HashMap<String, String> param);
	public int getCount(HashMap<String, String> param);
	
	public ArrayList<ResumetbDTO> resumeAll(HashMap<String, String> param); //일반 회원 계정 기준 이력서 전체 검색
	public void resumeAllDelete(HashMap<String, String> param); //탈퇴하려는 회원의 이력서 전체 삭제
}
