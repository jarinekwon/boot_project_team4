package com.boot.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.CoinfotbDAO;
import com.boot.dto.CoinfotbDTO;

import lombok.extern.slf4j.Slf4j;

@Service("CoinfotbService")
@Slf4j
public class CoinfotbServiceImpl implements CoinfotbService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(HashMap<String, String> param) {
		log.info("@# CoinfotbServiceImpl insert");
		      
		CoinfotbDAO dao = sqlSession.getMapper(CoinfotbDAO.class);
		dao.insert(param);
	}

	@Override
	public CoinfotbDTO Coinfotblist(HashMap<String, String> param) {
		log.info("@# CoinfotbServiceImpl Coinfotblist");
		      
		CoinfotbDAO dao = sqlSession.getMapper(CoinfotbDAO.class);
		CoinfotbDTO dto = dao.Coinfotblist(param);
		      
		return dto;
	}

	@Override
	public void Coinmodify(HashMap<String, String> param) {
		log.info("@# CoinfotbServiceImpl Modify");
		
		CoinfotbDAO dao = sqlSession.getMapper(CoinfotbDAO.class);
		dao.Coinmodify(param);
	}

	@Override
	public CoinfotbDTO Coinfotbinfo(HashMap<String, String> param) {
		log.info("@# CoinfotbServiceImpl Modify");
		
		CoinfotbDAO dao = sqlSession.getMapper(CoinfotbDAO.class);
		return dao.Coinfotbinfo(param);
	}

	@Override
	public void delete(HashMap<String, String> param) {
		log.info("@# CoinfotbServiceImpl delete");
		
		CoinfotbDAO dao = sqlSession.getMapper(CoinfotbDAO.class);
		dao.delete(param);
	}
}







