package com.boot.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.ShowskilltbDAO;
import com.boot.dto.ShowskilltbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("ShowskillService")
public class ShowskillServiceImpl implements ShowskillService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<ShowskilltbDTO> selectAll() {
		log.info("@# ShowskillServiceImpl selectAll");
		
		ShowskilltbDAO dao = sqlSession.getMapper(ShowskilltbDAO.class);
		return dao.selectAll();
	}
}