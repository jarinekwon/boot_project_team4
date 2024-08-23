package com.boot.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.OffertbDTO;

@Mapper
public interface OffertbDAO {
	public void offersave(HashMap<String, String> param); // 포지전 제안 저장
	public ArrayList<OffertbDTO> offerlist(HashMap<String, String> param); //전체 포지션
	public int getTotalCount(HashMap<String, String> param); // 제안 받은 포지션 갯수
	public OffertbDTO offerValue(HashMap<String, String> param); //제안한 포지션 출력
	public void offerreply(HashMap<String, String> param); //포지션 제안 답변
	
	//기업 기준 포지션 제안 내역 확인
	public ArrayList<OffertbDTO> offerlist_c(HashMap<String, String> param);
	public int getTotalCount_c(HashMap<String, String> param);
}