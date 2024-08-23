package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.CusertbDAO;
import com.boot.dao.PusertbDAO;
import com.boot.dto.CusertbDTO;

import lombok.extern.slf4j.Slf4j;

@Service("CusertbService")
@Slf4j
public class CusertbServiceImpl implements CusertbService {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void cRegisterInsert(HashMap<String, String> param) {
		log.info("@# registerInsert");
		CusertbDAO dao = sqlSession.getMapper(CusertbDAO.class);
		dao.cRegisterInsert(param);
	}

	@Override
	public ArrayList<CusertbDTO> cloginYn(HashMap<String, String> param) {
		CusertbDAO dao = sqlSession.getMapper(CusertbDAO.class);
		ArrayList<CusertbDTO> list = dao.cloginYn(param);
		
		return list;
	}

	@Override
	public int idchk(String cuserid) {
		CusertbDAO dao = sqlSession.getMapper(CusertbDAO.class);
		
		return dao.idchk(cuserid);
	}

   
	@Override
	public ArrayList<CusertbDTO> CPasswordYn(HashMap<String, String> param) {
		log.info("@# CusertbServiceImpl CPasswordYn");
		      
		CusertbDAO dao = sqlSession.getMapper(CusertbDAO.class);
		ArrayList<CusertbDTO> update = dao.CPasswordYn(param);
		   
		return update;
	}
	   
	@Override
	public void CModify(HashMap<String, String> param) {
		log.info("@# CusertbServiceImpl CModify");
		      
		CusertbDAO dao = sqlSession.getMapper(CusertbDAO.class);
		dao.CModify(param);
	}
	   
	@Override
	public CusertbDTO CInfoView(HashMap<String, String> param) {
		log.info("@# CusertbServiceImpl CInfoView");
		      
		CusertbDAO dao = sqlSession.getMapper(CusertbDAO.class);
		CusertbDTO dto = dao.CInfoView(param);
		      
		return dto;
	}

	@Override
	public void CDelete(HashMap<String, String> param) {
		log.info("@# CusertbServiceImpl CDelete");
	      
		CusertbDAO dao = sqlSession.getMapper(CusertbDAO.class);
		dao.CDelete(param);
	}
	
	@Override // 기업 아이디 찾기
	public ArrayList<CusertbDTO> cidfindYn(HashMap<String, String> param) {
		CusertbDAO dao = sqlSession.getMapper(CusertbDAO.class);
		ArrayList<CusertbDTO> list = dao.cidfindYn(param);
		
		return list;
	}

	@Override // 기업 비밀번호 변경하기
	public void cupdatePassword(HashMap<String, String> param) {
		CusertbDAO dao = sqlSession.getMapper(CusertbDAO.class);
		dao.cupdatePassword(param);
		
	}
}
