package com.boot.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.ScribetbDAO;
import com.boot.dto.ScribetbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("ScribeService")
public class ScribeServiceImpl implements ScribeService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void scribe_p_insert(HashMap<String, String> param) {
		log.info("@# ScribeServiceImpl scribe_p_insert");
		ScribetbDAO dao = sqlSession.getMapper(ScribetbDAO.class);
		log.info("@# param => "+param);
		dao.scribe_p_insert(param);
	}

	@Override
	public void scribe_p_delete(HashMap<String, String> param) {
		log.info("@# ScribeServiceImpl scribe_p_delete");
		ScribetbDAO dao = sqlSession.getMapper(ScribetbDAO.class);
		dao.scribe_p_delete(param);
	}

	@Override
	public ScribetbDTO scribe_p_select(HashMap<String, String> param) {
		log.info("@# ScribeServiceImpl scribe_p_select");
		ScribetbDAO dao = sqlSession.getMapper(ScribetbDAO.class);
		
		ScribetbDTO dto = dao.scribe_p_select(param);
		
		return dto;
	}

	@Override
	public List<ScribetbDTO> allselect_p(HashMap<String, String> param) {
		log.info("@# ScribeServiceImpl allselect");
		ScribetbDAO dao = sqlSession.getMapper(ScribetbDAO.class);
		
		return dao.allselect_p(param);
	}

	@Override
	public int allcount_p(HashMap<String, String> param) {
		log.info("@# ScribeServiceImpl allcount_p");
		ScribetbDAO dao = sqlSession.getMapper(ScribetbDAO.class);
		
		return dao.allcount_p(param);
	}

	@Override
	public void deleteAll_p(HashMap<String, String> param) {
		log.info("@# ScribeServiceImpl deleteAll_p");
		ScribetbDAO dao = sqlSession.getMapper(ScribetbDAO.class);
		
		dao.deleteAll_p(param);
	}

	@Override
	public void deleteAll_c(HashMap<String, String> param) {
		log.info("@# ScribeServiceImpl deleteAll_c");
		ScribetbDAO dao = sqlSession.getMapper(ScribetbDAO.class);
		
		dao.deleteAll_c(param);
	}
}