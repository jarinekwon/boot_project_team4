package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.OffertbDAO;
import com.boot.dto.OffertbDTO;

@Service("Offerservice")
public class OfferServiceImpl implements OfferService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void offersave(HashMap<String, String> param) {
		OffertbDAO dao = sqlSession.getMapper(OffertbDAO.class);
		dao.offersave(param);
	}

	@Override
	public ArrayList<OffertbDTO> offerlist(HashMap<String, String> param) {
		OffertbDAO dao = sqlSession.getMapper(OffertbDAO.class);
		return dao.offerlist(param);
	}

	@Override
	public int getTotalCount(HashMap<String, String> param) {
		OffertbDAO dao = sqlSession.getMapper(OffertbDAO.class);
		return dao.getTotalCount(param);
	}

	@Override
	public OffertbDTO offerValue(HashMap<String, String> param) {
		OffertbDAO dao = sqlSession.getMapper(OffertbDAO.class);
		return dao.offerValue(param);
	}

	@Override
	public void offerreply(HashMap<String, String> param) {
		OffertbDAO dao = sqlSession.getMapper(OffertbDAO.class);
		dao.offerreply(param);
	}

	@Override
	public ArrayList<OffertbDTO> offerlist_c(HashMap<String, String> param) {
		OffertbDAO dao = sqlSession.getMapper(OffertbDAO.class);
		return dao.offerlist_c(param);
	}

	@Override
	public int getTotalCount_c(HashMap<String, String> param) {
		OffertbDAO dao = sqlSession.getMapper(OffertbDAO.class);
		return dao.getTotalCount_c(param);
	}
}