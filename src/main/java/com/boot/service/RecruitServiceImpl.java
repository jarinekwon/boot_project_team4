package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.JobposttbDAO;
import com.boot.dto.JobposttbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("RecruitService")
public class RecruitServiceImpl implements RecruitService {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void recruitadd(HashMap<String, String> param) {
		log.info("@# RecruitServiceImpl write");
		
		JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
		dao.recruitadd(param);
	}

	@Override
	public JobposttbDTO recruitinfo(HashMap<String, String> param) {
		
		JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
		return dao.recruitinfo(param);
	}

	@Override
	public ArrayList<JobposttbDTO> recruitlist(HashMap<String, String> param) {
		
		JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
		return dao.recruitlist(param);
	}

	@Override
	public void recruitupdate(HashMap<String, String> param) {
		log.info("@# BoardServiceImpl update");
		log.info("@# param=>"+param);
		
		JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
		dao.recruitupdate(param);
	}
	
	@Override
	public void recruitdelete(HashMap<String, String> param) {
		log.info("@# BoardServiceImpl delete");
		log.info("@# param=>"+param);
		
		JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
		dao.recruitdelete(param);
	}
}







