package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.dao.ResumetbDAO;
import com.boot.dto.ResumetbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("ResumeService")
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void resumesave(HashMap<String, String> param) {
    	ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
    	dao.resumesave(param);
    }

	@Override
	public ArrayList<ResumetbDTO> resumelist(HashMap<String, String> param) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		log.info("param => " + param);
		ArrayList<ResumetbDTO> list1 = dao.resumelist(param);
		log.info("list1 => " + list1);
		return list1;
	}

	@Override
	public ResumetbDTO resumeselect(HashMap<String, String> param) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		ResumetbDTO update = dao.resumeselect(param);
		return update;
	}

	@Override
	public int getMaxProno(String puserid) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		return dao.getMaxProno(puserid);
	}

	@Override
	public void resumedelete(HashMap<String, String> param) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		dao.resumedelete(param);
	}

	@Override
	public ResumetbDTO resume_view(HashMap<String, String> param) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		ResumetbDTO dto = dao.resume_view(param);
		return dto;
	}

	@Override
    public ArrayList<ResumetbDTO> getResumeList(String puserid) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
        ArrayList<ResumetbDTO> list = dao.getResumeList(puserid);
        return list;
    }

	@Override
	public int getTotalCount(HashMap<String, String> param) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		
		return dao.getTotalCount(param);
	}

	@Override
	public void resumeupdate(HashMap<String, String> param) {
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		dao.resumeupdate(param);
	}
	
	@Override
	@Transactional
	public void setRepresentative(String puserid, Long prono, Long imgno) {
	    ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
	    dao.resetAllRepresentative(puserid); // 모든 이력서의 대표 이력서 상태 해제

	    HashMap<String, Object> param = new HashMap<>();
	    param.put("puserid", puserid);
	    param.put("prono", prono);
	    param.put("imgno", imgno);
	    dao.setRepresentative(param); // 지정된 이력서를 대표 이력서로 설정
	}
	
	@Override
	public ArrayList<ResumetbDTO> skillselect(HashMap<String, String> param) {
		log.info("@# ResumeServiceImpl skillselect");
		
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		ArrayList<ResumetbDTO> list = dao.skillselect(param);
		
		return list;
	}

	@Override
	public int getCount(HashMap<String, String> param) {
		log.info("@# count");
		
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		int count = dao.getCount(param);
		
		return count;
	}

	@Override
	public ArrayList<ResumetbDTO> resumeAll(HashMap<String, String> param) {
		log.info("@# ResumeServiceImpl resumeAll");
		
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		return dao.resumeAll(param);
	}

	@Override
	public void resumeAllDelete(HashMap<String, String> param) {
		log.info("@# ResumeServiceImpl resumeAllDelete");
		
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		dao.resumeAllDelete(param);
	}

	@Override
	public ResumetbDTO resume_v(HashMap<String, String> param) {
		log.info("@# ResumeServiceImpl resume_v");
		
		ResumetbDAO dao = sqlSession.getMapper(ResumetbDAO.class);
		ResumetbDTO list = dao.resume_v(param);
		log.info("@# ResumeServiceImpl resume_v list!! => "+list);
		
		return list;
	}
}