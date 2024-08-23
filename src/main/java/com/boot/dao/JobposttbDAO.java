package com.boot.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.dto.JobposttbDTO;

@Mapper
public interface JobposttbDAO {
	public ArrayList<JobposttbDTO> jobselect(HashMap<String, String> param); //등록된 공고 출력
	public int getCount(HashMap<String, String> param); //등록된 공고 수
	public ArrayList<JobposttbDTO> mainlist(); //메인화면
	
	//기업 기준
	public void recruitadd(HashMap<String, String> param); //등록
	public JobposttbDTO recruitinfo(HashMap<String, String> param); //등록된 정보 조회
	public ArrayList<JobposttbDTO> recruitlist(HashMap<String, String> param); //등록한 공고 목록 출력
	public void recruitupdate(HashMap<String, String> param); //등록한 공고 수정
	public void recruitdelete(HashMap<String, String> param); //등록한 공고 삭제
	
	//이력서 관련
	public ArrayList<JobposttbDTO> jobaplylist(HashMap<String, String> param);
	public JobposttbDTO jobaply(HashMap<String, String> param);
	public JobposttbDTO resumetbview(HashMap<String, String> param);
	
	public void increaseViewCount(@Param("cuserid") String cuserid, @Param("csrno") String csrno, @Param("jobno") String jobno);
	public void incrementSupno(HashMap<String, String> param);
	
	public List<JobposttbDTO> getSimilarTitles(@Param("keywords") List<String> keywords);
	public ArrayList<JobposttbDTO> mainAllList();
	
	public void decreaseSupno(HashMap<String, String> param); //탈퇴 회원으로 인한 supno -= 1;
	public void deleteAll_c(HashMap<String, String> param); //탈퇴 기업 회원의 공고 내역 삭제
	
	public List<JobposttbDTO> rviewrecruitlist(HashMap<String, String> param);
	public int getTotalCount(HashMap<String, String> param);
}
