package com.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.JobsorttbDAO;
import com.boot.dto.JobsorttbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("JobsorttbService")
public class JobsorttbServiceImpl implements JobsorttbService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<JobsorttbDTO> jobsortAll() {
		log.info("@# jobsort");
		JobsorttbDAO dao = sqlSession.getMapper(JobsorttbDAO.class); 
		
		return dao.jobsortAll();
	}
}