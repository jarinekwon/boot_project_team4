package com.boot.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.ScraptbDAO;
import com.boot.dto.ScraptbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("ScrapService")
public class ScrapServiceImpl implements ScrapService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void scrap_p_insert(HashMap<String, String> param) {
		log.info("@# ScrapServiceImpl scrap_p_insert");
		ScraptbDAO dao = sqlSession.getMapper(ScraptbDAO.class);
		log.info("@# param => "+param);
		dao.scrap_p_insert(param);
	}

	@Override
	public void scrap_p_delete(HashMap<String, String> param) {
		log.info("@# ScrapServiceImpl scrap_p_delete");
		ScraptbDAO dao = sqlSession.getMapper(ScraptbDAO.class);
		dao.scrap_p_delete(param);
	}

	@Override
	public ScraptbDTO scrap_p_select(HashMap<String, String> param) {
		log.info("@# ScrapServiceImpl scrap_p_select");
		ScraptbDAO dao = sqlSession.getMapper(ScraptbDAO.class);
		
		ScraptbDTO dto = dao.scrap_p_select(param);
		log.info("@# ScrapServiceImpl scrap_p_select!!!!!"+dao.scrap_p_select(param));
		
		return dto;
	}

	@Override
	public List<ScraptbDTO> allselect_p(HashMap<String, String> param) {
		log.info("@# ScrapServiceImpl allselect");
		ScraptbDAO dao = sqlSession.getMapper(ScraptbDAO.class);
		
		return dao.allselect_p(param);
	}

	@Override
	public int allcount_p(HashMap<String, String> param) {
		log.info("@# ScrapServiceImpl allcount_p");
		ScraptbDAO dao = sqlSession.getMapper(ScraptbDAO.class);
		
		return dao.allcount_p(param);
	}

	@Override
	public void deleteAll_p(HashMap<String, String> param) {
		log.info("@# ScrapServiceImpl deleteAll_p");
		ScraptbDAO dao = sqlSession.getMapper(ScraptbDAO.class);
		
		dao.deleteAll_p(param);
	}

	@Override
	public void deleteAll_c(HashMap<String, String> param) {
		log.info("@# ScrapServiceImpl deleteAll_c");
		ScraptbDAO dao = sqlSession.getMapper(ScraptbDAO.class);
		
		dao.deleteAll_c(param);
	}

}