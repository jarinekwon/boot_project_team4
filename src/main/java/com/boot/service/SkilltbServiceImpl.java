package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.SkilltbDAO;
import com.boot.dto.SkilltbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("SkilltbService")
public class SkilltbServiceImpl implements SkilltbService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void skillinsert(HashMap<String, String> param) {
		log.info("@# skillinsert");
		
		SkilltbDAO dao = sqlSession.getMapper(SkilltbDAO.class);
		dao.skillinsert(param);
	}

	@Override
	public ArrayList<SkilltbDTO> select_resume(HashMap<String, String> param) {
		log.info("@# select_resume");
		SkilltbDAO dao = sqlSession.getMapper(SkilltbDAO.class);
		
		return dao.select_resume(param);
	}

	@Override
	public void skilldelete(HashMap<String, String> param) {
		log.info("SkilltbServiceImpl skilldelete");
		SkilltbDAO dao = sqlSession.getMapper(SkilltbDAO.class);
		dao.skilldelete(param);
		
	}
}