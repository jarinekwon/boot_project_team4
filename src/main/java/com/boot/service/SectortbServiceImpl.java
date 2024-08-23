package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.SectortbDAO;
import com.boot.dto.SectortbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("SectortbService")
public class SectortbServiceImpl implements SectortbService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<SectortbDTO> Sectortblist() {
		log.info("@# SectortbServiceImpl Sectortblist");
		
		SectortbDAO dao = sqlSession.getMapper(SectortbDAO.class);
		ArrayList<SectortbDTO> dto = dao.Sectortblist();

		return dto;
	}
}