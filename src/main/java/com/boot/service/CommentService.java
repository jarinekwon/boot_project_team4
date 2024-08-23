package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.dto.CommenttbDTO;

public interface CommentService {
	public void save(HashMap<String, String> param);
	public ArrayList<CommenttbDTO> findAll(HashMap<String, String> param);
	
	//탈퇴 회원 댓글 삭제
	public void delete_p_All(HashMap<String, String> param);
	public void modify(HashMap<String, String> param);
    public void delete(HashMap<String, String> param);
}
