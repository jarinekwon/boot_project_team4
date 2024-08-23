package com.boot.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.MusertbDAO;
import com.boot.dto.MusertbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("MusertbService")
public class MusertbServiceImpl implements MusertbService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MusertbDTO mloginYn(HashMap<String, String> param) {
		log.info("@# mloginYn");
		MusertbDAO dao = sqlSession.getMapper(MusertbDAO.class);
		
		return dao.mloginYn(param);
	}

}
