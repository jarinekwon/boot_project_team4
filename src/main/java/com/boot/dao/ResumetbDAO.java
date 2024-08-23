package com.boot.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.ResumetbDTO;

@Mapper
public interface ResumetbDAO {
    public void resumesave(HashMap<String, String> param); //이력서 저장
	public ArrayList<ResumetbDTO> resumelist(HashMap<String, String> param); //저장된 이력서 리스트
	public ResumetbDTO resumeselect(HashMap<String, String> param); //선택한 리스트 출력
	public int getMaxProno(String puserid); //이력서 저장 후 이력서 번호 최댓값 가져오기
	public void resumedelete(HashMap<String, String> param); //선택한 이력서 삭제
	public ResumetbDTO resume_view(HashMap<String, String> param); //이력서 뷰어에서 출력
	public ArrayList<ResumetbDTO> getResumeList(String puserid); //지원하기 팝업창에서 이력서 목록 출력
	public int getTotalCount(HashMap<String, String> param); // 이력서 갯수
	public void resumeupdate(HashMap<String, String> param); //이력서 업데이트
	
    void resetAllRepresentative(String puserid); // 모든 이력서의 대표 이력서 상태 해제
    void setRepresentative(HashMap<String, Object> param); // 지정된 이력서를 대표 이력서로 설정
    
    public ResumetbDTO resume_v(HashMap<String, String> param); //인재 검색의 이력서 상세 출력
    public ArrayList<ResumetbDTO> skillselect(HashMap<String, String> param); //대표 이력서 출력
	public int getCount(HashMap<String, String> param); //대표 이력서 갯수
	
	public ArrayList<ResumetbDTO> resumeAll(HashMap<String, String> param); //탈퇴하려는 일반 회원 계정 기준 이력서 전체 검색
	public void resumeAllDelete(HashMap<String, String> param); //탈퇴하려는 회원의 이력서 전체 삭제
}