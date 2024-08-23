package com.boot.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.CareertbDAO;
import com.boot.dto.CareertbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("CareertbService")
public class CareertbServiceImpl implements CareertbService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<CareertbDTO> selectAll() {
		log.info("@# CareertbServiceImpl select");
		CareertbDAO dao = sqlSession.getMapper(CareertbDAO.class);
		
		return dao.selectAll();
	}
}