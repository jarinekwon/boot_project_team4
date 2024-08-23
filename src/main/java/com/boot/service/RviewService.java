package com.boot.service;

import java.util.HashMap;
import java.util.List;

import com.boot.dto.RviewtbDTO;

public interface RviewService {
	public List<RviewtbDTO> rviewlist(HashMap<String, String> param);
	public int c_rview(RviewtbDTO rviewtbDTO);
	public int getTotalCount(HashMap<String, String> param);
}
