package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.BoardtbDAO;
import com.boot.dto.BoardtbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("BoardService")
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<BoardtbDTO> list() {
		log.info("@# BoardServiceImpl list");
		
		BoardtbDAO dao = sqlSession.getMapper(BoardtbDAO.class);
		ArrayList<BoardtbDTO> list=dao.list();
		
		return list;
	}

	@Override
//	public void write(HashMap<String, String> param) {
//	파일업로드는 파라미터를 DTO 사용
	public void write(BoardtbDTO boardDTO) {
		
		log.info("@# BoardServiceImpl write");
		
		BoardtbDAO dao = sqlSession.getMapper(BoardtbDAO.class);
		//BoardAttachDAO adao = sqlSession.getMapper(BoardAttachDAO.class);
//		dao.write(param);
		dao.write(boardDTO);
		log.info("@# boardDTO=>"+boardDTO);
		
//		첨부파일 있는지 체크
		/*
		log.info("@# boardDTO.getAttachList()=>"+boardDTO.getAttachList());
		if (boardDTO.getAttachList() == null || boardDTO.getAttachList().size() == 0) {
			log.info("@# null");
			return;
		}
		
//		첨부파일이 있는 경우 처리
		boardDTO.getAttachList().forEach(attach -> {
			attach.setBoardno(boardDTO.getBoardno());
			adao.insertFile(attach);
		});
		*/
	}
	
	@Override
	public BoardtbDTO modify_view(HashMap<String, String> param) {
		log.info("@# BoardServiceImpl modify_view");
		
		BoardtbDAO dao = sqlSession.getMapper(BoardtbDAO.class);
		BoardtbDTO dto = dao.modify_view(param);
		
		return dto;
	}
	
	@Override
	public BoardtbDTO contentView(HashMap<String, String> param) {
		log.info("@# BoardServiceImpl content_view");
		
		BoardtbDAO dao = sqlSession.getMapper(BoardtbDAO.class);
		BoardtbDTO dto = dao.contentView(param);
		
		return dto;
	}

	@Override
	public void modify(HashMap<String, String> param) {
		log.info("@# BoardServiceImpl modify");
		
		BoardtbDAO dao = sqlSession.getMapper(BoardtbDAO.class);
		dao.modify(param);
	}

	@Override
	public void delete(HashMap<String, String> param) {
		log.info("@# BoardServiceImpl delete");
		log.info("@# param=>"+param);
		
		BoardtbDAO dao = sqlSession.getMapper(BoardtbDAO.class);
		//BoardAttachDAO attachDAO = sqlSession.getMapper(BoardAttachDAO.class);
		
		//attachDAO.deleteFile(param.get("boardno"));
		dao.delete(param);
	}


	@Override
	public void upHit(HashMap<String, String> param) {
		log.info("@# BoardServiceImpl upHit");
		
		BoardtbDAO dao = sqlSession.getMapper(BoardtbDAO.class);
		dao.upHit(param);
	}
	
	@Override
	public ArrayList<BoardtbDTO> select_boardno(HashMap<String, String> param) {
		log.info("@# BoardServiceImpl select_boardno");
		
		BoardtbDAO dao = sqlSession.getMapper(BoardtbDAO.class);
		return dao.select_boardno(param);
	}
	
	@Override
	public void delete_p_All(HashMap<String, String> param) {
		log.info("@# BoardServiceImpl delete_p_All");
		
		BoardtbDAO dao = sqlSession.getMapper(BoardtbDAO.class);
		dao.delete_p_All(param);
	}
}