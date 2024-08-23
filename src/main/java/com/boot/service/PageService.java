package com.boot.service;

import java.util.ArrayList;

import com.boot.dto.BoardtbDTO;
import com.boot.dto.Criteria;

public interface PageService {
	public ArrayList<BoardtbDTO> listWithPaging(Criteria cri); //일반 검색
	public ArrayList<BoardtbDTO> listWithPaging_writer(Criteria cri); //작성자 기준 검색
//	public int getTotalCount();
	public int getTotalCount(Criteria cri);
}
