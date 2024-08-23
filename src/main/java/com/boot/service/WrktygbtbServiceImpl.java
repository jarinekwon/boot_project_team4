package com.boot.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.WrktygbtbDAO;
import com.boot.dto.WrktygbtbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("WrktygbtbService")
public class WrktygbtbServiceImpl implements WrktygbtbService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<WrktygbtbDTO> selectAll() {
		log.info("@# WrktygbtbServiceImpl selectAll");
		WrktygbtbDAO dao = sqlSession.getMapper(WrktygbtbDAO.class);

		return dao.selectAll();
	}
}