package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.boot.dto.JobposttbDTO;

public interface JobposttbService {
	public ArrayList<JobposttbDTO> jobselect(HashMap<String, String> param);
	public int getCount(HashMap<String, String> param);
	public ArrayList<JobposttbDTO> mainlist();
	
	public JobposttbDTO jobaply(HashMap<String, String> param);
	public void increaseViewCount(String cuserid, String csrno, String jobno);
	
	public List<JobposttbDTO> getSimilarTitles(List<String> keywords);
	public ArrayList<JobposttbDTO> mainAllList();
	
	public void decreaseSupno(HashMap<String, String> param); //탈퇴 회원으로 인한 지원자 수 -1
	public void deleteAll_c(HashMap<String, String> param);
	
	public List<JobposttbDTO> rviewrecruitlist(HashMap<String, String> param);
	public int getTotalCount(HashMap<String, String> param);
}
