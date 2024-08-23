package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.PusertbDAO;
import com.boot.dto.PusertbDTO;

import lombok.extern.slf4j.Slf4j;

@Service("PusertbService")
@Slf4j
public class PusertbServiceImpl implements PusertbService {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void pRegisterInsert(HashMap<String, String> param) {
		log.info("@# registerInsert");
		PusertbDAO dao = sqlSession.getMapper(PusertbDAO.class);
		dao.pRegisterInsert(param);
	}

	@Override
	public ArrayList<PusertbDTO> ploginYn(HashMap<String, String> param) {
		PusertbDAO dao = sqlSession.getMapper(PusertbDAO.class);
		ArrayList<PusertbDTO> list = dao.ploginYn(param);
		
		return list;
	}

	@Override
	public int idchk(String puserid) {
		PusertbDAO dao = sqlSession.getMapper(PusertbDAO.class);
		
		return dao.idchk(puserid);
	}
	
	@Override
	public ArrayList<PusertbDTO> PPasswordYn(HashMap<String, String> param) {
		log.info("@# MyPageServiceImpl PPasswordYn - Params: " + param);

		PusertbDAO dao = sqlSession.getMapper(PusertbDAO.class);
		ArrayList<PusertbDTO> update = dao.PPasswordYn(param);
		log.info("@# PusertbServiceImpl PPasswordYn - update: " + update);
		
		return update;
	}
   
	@Override
	public PusertbDTO PInfoView(HashMap<String, String> param) {
		log.info("@# PusertbServiceImpl PInfoView");
		  
		PusertbDAO dao = sqlSession.getMapper(PusertbDAO.class);
		  
		PusertbDTO dto = dao.PInfoView(param);
		log.info("@# MyPageServiceImpl dto => "+dto);
		      
		return dto;
	}
	   
	@Override
	public void PModify(HashMap<String, String> param) {
		log.info("@# PusertbServiceImpl PModify");
		      
		PusertbDAO dao = sqlSession.getMapper(PusertbDAO.class);
		dao.PModify(param);
	}

	@Override
	public void PDelete(HashMap<String, String> param) {
		log.info("@# PusertbServiceImpl PDelete");
		log.info("@# param => " + param);
		PusertbDAO dao = sqlSession.getMapper(PusertbDAO.class);
		dao.PDelete(param);
	}
	
	@Override // 일반 아이디 찾기 
	public ArrayList<PusertbDTO> pidfindYn(HashMap<String, String> param) {
		PusertbDAO dao = sqlSession.getMapper(PusertbDAO.class);
		ArrayList<PusertbDTO> list = dao.pidfindYn(param);
		
		return list;
	}

	@Override // 일반 비밀번호 변경하기 
	public void pupdatePassword(HashMap<String, String> param) {
		
		PusertbDAO dao = sqlSession.getMapper(PusertbDAO.class);
		dao.pupdatePassword(param);
	}

	@Override
	public ArrayList<PusertbDTO> PInfoAll(HashMap<String, String> param) {
		PusertbDAO dao = sqlSession.getMapper(PusertbDAO.class);
		return dao.PInfoAll(param);
	}

	@Override
	public String puserphone(HashMap<String, String> param) {
		PusertbDAO dao = sqlSession.getMapper(PusertbDAO.class);
		return dao.puserphone(param).replaceAll("-", "");
	}
}
