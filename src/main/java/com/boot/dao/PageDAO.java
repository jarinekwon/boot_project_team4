package com.boot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.BoardtbDTO;
import com.boot.dto.Criteria;

//실행시 매퍼파일을 읽어 들이도록 지정
@Mapper
public interface PageDAO {
//	Criteria 객체를 이용해서 페이징 처리
	public ArrayList<BoardtbDTO> listWithPaging(Criteria cri); //일반 검색
	public ArrayList<BoardtbDTO> listWithPaging_writer(Criteria cri); //작성자 기준 검색
//	public int getTotalCount();
	public int getTotalCount(Criteria cri);
}
















