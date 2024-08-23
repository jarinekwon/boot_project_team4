package com.boot.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.PageDAO;
import com.boot.dto.BoardtbDTO;
import com.boot.dto.Criteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("PageService")
public class PageServiceImpl implements PageService{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<BoardtbDTO> listWithPaging(Criteria cri) {
		int offset = (cri.getPageNum() - 1) * cri.getAmount();
		log.info("@# PageServiceImpl listWithPaging");
		log.info("@# cri=>"+cri);
		cri.setOffset(offset);
		PageDAO dao = sqlSession.getMapper(PageDAO.class);
		ArrayList<BoardtbDTO> list=dao.listWithPaging(cri);
		
		return list;
	}

	@Override
//	public int getTotalCount() {
	public int getTotalCount(Criteria cri) {
		log.info("@# PageServiceImpl getTotalCount");
		
		PageDAO dao = sqlSession.getMapper(PageDAO.class);
//		int total=dao.getTotalCount();
		int total=dao.getTotalCount(cri);
		
		return total;
	}

	@Override
	public ArrayList<BoardtbDTO> listWithPaging_writer(Criteria cri) {
		int offset = (cri.getPageNum() - 1) * cri.getAmount();
		log.info("@# PageServiceImpl listWithPaging_writer");
		log.info("@# cri=>"+cri);
		cri.setOffset(offset);
		PageDAO dao = sqlSession.getMapper(PageDAO.class);
		ArrayList<BoardtbDTO> list = dao.listWithPaging_writer(cri);
		
		return list;
	}
}







