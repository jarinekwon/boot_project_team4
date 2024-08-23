package com.boot.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.EdugbtbDAO;
import com.boot.dto.EdugbtbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("EdugbtbService")
public class EdugbtbServiceImpl implements EdugbtbService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<EdugbtbDTO> selectAll() {
		log.info("@# EdugbtbServiceImpl select");
		
		EdugbtbDAO dao = sqlSession.getMapper(EdugbtbDAO.class);
		
		return dao.selectAll();
	}
	
}
