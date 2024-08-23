package com.boot.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.BoardtbDTO;

//실행시 매퍼파일을 읽어 들이도록 지정
@Mapper
public interface BoardtbDAO {
	public ArrayList<BoardtbDTO> list();
//	public void write(HashMap<String, String> param);
	public void write(BoardtbDTO boardDTO);
	public BoardtbDTO modify_view(HashMap<String, String> param);
	public BoardtbDTO contentView(HashMap<String, String> param);
	public void modify(HashMap<String, String> param);
	public void delete(HashMap<String, String> param);
	public void upHit(HashMap<String, String> param);
	
	//회원 탈퇴
	public ArrayList<BoardtbDTO> select_boardno(HashMap<String, String> param); //댓글 삭제를 위한 boardno 가져오기
	public void delete_p_All(HashMap<String, String> param);
}
















