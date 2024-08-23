package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.RcareerDAO;
import com.boot.dto.RcareerDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("RcareerService")
public class RcareerServiceImpl implements RcareerService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void addRcareer(HashMap<String, String> param) {
        RcareerDAO rcareerDAO = sqlSession.getMapper(RcareerDAO.class);
        rcareerDAO.createRcareer(param);
    }

    @Override
    public ArrayList<RcareerDTO> selectRcareer(HashMap<String, String> param) {
        RcareerDAO rcareerDAO = sqlSession.getMapper(RcareerDAO.class);
        return rcareerDAO.selectRcareer(param);
    }

	@Override
	public void delete_career(HashMap<String, String> param) {
		RcareerDAO dao = sqlSession.getMapper(RcareerDAO.class);
		dao.delete_career(param);
	}

	@Override
	public void delete_career_All(HashMap<String, String> param) {
		RcareerDAO dao = sqlSession.getMapper(RcareerDAO.class);
		dao.delete_career_All(param);
	}
}