package com.boot.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.RviewtbDAO;
import com.boot.dto.RviewtbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("RviewService")
public class RviewServiceImpl implements RviewService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<RviewtbDTO> rviewlist(HashMap<String, String> param) {
		log.info("@# RviewServiceImpl rviewlist");
		RviewtbDAO dao = sqlSession.getMapper(RviewtbDAO.class);
		
		return dao.rviewlist(param);
	}

	@Override
	public int c_rview(RviewtbDTO rviewtbDTO) {
		log.info("@# RviewServiceImpl c_rview");
		RviewtbDAO dao = sqlSession.getMapper(RviewtbDAO.class);
		
		return dao.c_rview(rviewtbDTO);
	}

	@Override
	public int getTotalCount(HashMap<String, String> param) {
		log.info("@# getTotalCount");
		
		RviewtbDAO dao = sqlSession.getMapper(RviewtbDAO.class);
		
		return dao.getTotalCount(param);
	}
	
}
