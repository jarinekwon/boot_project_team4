package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.JobaplytbDAO;
import com.boot.dao.JobposttbDAO;
import com.boot.dto.JobaplytbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("JobaplyService")
public class JobaplyServiceImpl implements JobaplyService{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<JobaplytbDTO> jobaplylist_p(HashMap<String, String> param) {
		log.info("@# JobaplyServiceImpl jobaplylist_p");
		
		JobaplytbDAO dao = sqlSession.getMapper(JobaplytbDAO.class);
		ArrayList<JobaplytbDTO> list = dao.jobaplylist_p(param);
	
		return list;
	}

	@Override
	public ArrayList<JobaplytbDTO> jobaplylist(HashMap<String, String> param) {
		log.info("@# JobaplyServiceImpl jobaplylist");
		
		JobaplytbDAO dao = sqlSession.getMapper(JobaplytbDAO.class);
		ArrayList<JobaplytbDTO> list = dao.jobaplylist(param);
		return list;
	}

	@Override
	public int jobaplylist_count(HashMap<String, String> param) {
		log.info("@# JobaplyServiceImpl jobaplylist");
		
		JobaplytbDAO dao = sqlSession.getMapper(JobaplytbDAO.class);
		
		return dao.jobaplylist_count(param);
	}

	@Override
	public void jobaply_prcnt(HashMap<String, String> param) {
		JobaplytbDAO dao = sqlSession.getMapper(JobaplytbDAO.class);
		
		dao.jobaply_prcnt(param);
	}

	@Override
	public int getTotalCount(HashMap<String, String> param) {
		JobaplytbDAO dao = sqlSession.getMapper(JobaplytbDAO.class);
		return dao.getTotalCount(param);
	}

	@Override
	public void insertjobapply(HashMap<String, String> param) {
		JobaplytbDAO dao = sqlSession.getMapper(JobaplytbDAO.class);
		log.info("@# JobaplyServiceImpl insertjobapply param => "+param);
		dao.insertjobapply(param);
	}

	@Override
	public int jobaplycnt(HashMap<String, String> param) {
		JobaplytbDAO dao = sqlSession.getMapper(JobaplytbDAO.class);
		log.info("@# JobaplyServiceImpl jobaplycnt param => "+param);
		
		return dao.jobaplycnt(param);
	}

	@Override
	public void jobaply_delete(HashMap<String, String> param) {
		log.info("@# JobaplyServiceImpl jobaply_delete");
		JobaplytbDAO dao = sqlSession.getMapper(JobaplytbDAO.class);
		dao.jobaply_delete(param);
	}
	
	@Override
    public void incrementSupno(HashMap<String, String> param) { 
        log.info("@# incrementSupno");
        JobposttbDAO dao = sqlSession.getMapper(JobposttbDAO.class);
        dao.incrementSupno(param);
    }
	
	@Override
	public List<JobaplytbDTO> getGenderStatistics(HashMap<String, String> param) {
		log.info("@# getGenderStatistics");
        JobaplytbDAO dao = sqlSession.getMapper(JobaplytbDAO.class);

        return dao.getGenderStatistics(param);
	}
	
	@Override
	public List<JobaplytbDTO> getAgeStatistics(HashMap<String, String> param) {
		log.info("@# getAgeStatistics");
        JobaplytbDAO dao = sqlSession.getMapper(JobaplytbDAO.class);

        return dao.getAgeStatistics(param);
	}
	
	@Override
	public List<JobaplytbDTO> getEducationStatistics(HashMap<String, String> param) {
		log.info("@# getEducationStatistics");
        JobaplytbDAO dao = sqlSession.getMapper(JobaplytbDAO.class);

        return dao.getEducationStatistics(param);
	}

	@Override
	public ArrayList<JobaplytbDTO> jobaply_p_selectAll(HashMap<String, String> param) {
		log.info("@# jobaply_p_selectAll");
        JobaplytbDAO dao = sqlSession.getMapper(JobaplytbDAO.class);

        return dao.jobaply_p_selectAll(param);
	}

	@Override
	public void jobaply_p_all_delete(HashMap<String, String> param) {
		log.info("@# jobaply_p_all_delete");
        JobaplytbDAO dao = sqlSession.getMapper(JobaplytbDAO.class);

        dao.jobaply_p_all_delete(param);
	}
}