package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.JobposttbDAO;
import com.boot.dto.JobposttbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("JobposttbService")
public class JobposttbServiceImpl implements JobposttbService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<JobposttbDTO> jobselect(HashMap<String, String> param) {
		log.info("@# JobposttbServiceImpl jobselect");
		
		JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
		ArrayList<JobposttbDTO> list = dao.jobselect(param);
		
		return list;
	}

	@Override
	public int getCount(HashMap<String, String> param) {
		log.info("@# count");
		JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
		int count = dao.getCount(param);
		return count;
	}

	@Override
	public ArrayList<JobposttbDTO> mainlist() {
		log.info("@# JobposttbServiceImpl list");
		
		JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
		ArrayList<JobposttbDTO> list = dao.mainlist();
		
		return list;
	}
	
	@Override
	public JobposttbDTO jobaply(HashMap<String, String> param) {
		JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
		JobposttbDTO dto = dao.jobaply(param);
		return dto;
	}

	@Override
	public void increaseViewCount(String cuserid, String csrno, String jobno) {
        log.info("Increasing view count for jobno: " + jobno + ", cuserid: " + cuserid);
        JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
        dao.increaseViewCount(cuserid, csrno, jobno);
	}
	
	@Override
    public List<JobposttbDTO> getSimilarTitles(List<String> keywords) {
       JobposttbDAO dao=sqlSession.getMapper(JobposttbDAO.class);
       
        return dao.getSimilarTitles(keywords);
    }

	@Override
	public ArrayList<JobposttbDTO> mainAllList() {
		log.info("@# JobposttbServiceImpl mainalllist");
      
		JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
		ArrayList<JobposttbDTO> list = dao.mainAllList();
      
		return list;
   }

	@Override
	public void decreaseSupno(HashMap<String, String> param) {
		log.info("@# JobposttbServiceImpl decreaseSupno");
	      
		JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
		dao.decreaseSupno(param);
	}

	@Override
	public void deleteAll_c(HashMap<String, String> param) {
		log.info("@# JobposttbServiceImpl deleteAll_c");
	      
		JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
		dao.deleteAll_c(param);
	}

	@Override
	public List<JobposttbDTO> rviewrecruitlist(HashMap<String, String> param) {
		log.info("@# JobposttbServiceImple rviewrecruitlist");
		
		JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
		List<JobposttbDTO> list = dao.rviewrecruitlist(param);
		
		return list;
	}

	@Override
	public int getTotalCount(HashMap<String, String> param) {
		log.info("@# getTotalCount");
		
		JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
		
		return dao.getTotalCount(param);
	}
}