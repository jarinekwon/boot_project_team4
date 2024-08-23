package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.dto.OffertbDTO;

public interface OfferService {
	public void offersave(HashMap<String, String> param);
	public ArrayList<OffertbDTO> offerlist(HashMap<String, String> param);
	public int getTotalCount(HashMap<String, String> param);
	public OffertbDTO offerValue(HashMap<String, String> param);
	public void offerreply(HashMap<String, String> param);
	
	//기업 기준 포지션 확인
	public ArrayList<OffertbDTO> offerlist_c(HashMap<String, String> param);
	public int getTotalCount_c(HashMap<String, String> param);
}