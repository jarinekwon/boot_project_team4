package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.dto.JobposttbDTO;

public interface RecruitService {
	public void recruitadd(HashMap<String, String> param); //등록
	public JobposttbDTO recruitinfo(HashMap<String, String> param); //등록된 정보 조회
	public ArrayList<JobposttbDTO> recruitlist(HashMap<String, String> param);
	public void recruitupdate(HashMap<String, String> param); //등록한 공고 수정
	public void recruitdelete(HashMap<String, String> param); //등록한 공고 삭제
}