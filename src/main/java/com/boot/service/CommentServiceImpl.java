package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.CommenttbDAO;
import com.boot.dto.CommenttbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("CommentService")
public class CommentServiceImpl implements CommentService{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void save(HashMap<String, String> param) {
		log.info("@# CommentServiceImpl save");
		
		CommenttbDAO dao = sqlSession.getMapper(CommenttbDAO.class);
		dao.save(param);
	}

	@Override
	public ArrayList<CommenttbDTO> findAll(HashMap<String, String> param) {
		log.info("@# CommentServiceImpl findAll");
		
		CommenttbDAO dao = sqlSession.getMapper(CommenttbDAO.class);

		ArrayList<CommenttbDTO> list = dao.findAll(param);
		
		return list;
	}

	@Override
	public void delete_p_All(HashMap<String, String> param) {
		log.info("@# CommentServiceImpl findAll");
		
		CommenttbDAO dao = sqlSession.getMapper(CommenttbDAO.class);

		dao.delete_p_All(param);
	}
	
	@Override
    public void modify(HashMap<String, String> param) {
        log.info("@# CommentServiceImpl modify");
        
        CommenttbDAO dao = sqlSession.getMapper(CommenttbDAO.class);
        
        dao.modify(param);
    }

    @Override
    public void delete(HashMap<String, String> param) {
        log.info("@# CommentServiceImpl delete");
        
        CommenttbDAO dao = sqlSession.getMapper(CommenttbDAO.class);
        
        dao.delete(param);
    }
}