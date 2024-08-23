package com.boot.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.JobaplytbDTO;

@Mapper
public interface JobaplytbDAO {
	public ArrayList<JobaplytbDTO> jobaplylist_p(HashMap<String, String> param);
	public int getTotalCount(HashMap<String, String> param);
	public void jobaply_prcnt(HashMap<String, String> param); //기업에서 이력서 확인시, prcnt = 1로 업데이트
	public void jobaply_delete(HashMap<String, String> param); //공고 삭제 시, 해당 공고의 지원 이력 삭제 
	public void insertjobapply(HashMap<String, String> param); //이력서 지원
	public int jobaplycnt(HashMap<String, String> param); //이력서 지원 여부 확인
	
	/* 기업에서 보는 지원자 현황 */
	public ArrayList<JobaplytbDTO> jobaplylist(HashMap<String, String> param);
	public int jobaplylist_count(HashMap<String, String> param);
	
	public List<JobaplytbDTO> getGenderStatistics(HashMap<String, String> param); // 성별 통계 조회
    public List<JobaplytbDTO> getAgeStatistics(HashMap<String, String> param);  // 연령 통계 조회
    public List<JobaplytbDTO> getEducationStatistics(HashMap<String, String> param);   // 학력 통계 조회
    
    public ArrayList<JobaplytbDTO> jobaply_p_selectAll(HashMap<String, String> param); //탈퇴하려는 일반회원 기준 지원이력 전체 조회
    public void jobaply_p_all_delete(HashMap<String, String> param); //탈퇴하려는 일반 회원 계정 기준 전체 지원 이력 삭제
}