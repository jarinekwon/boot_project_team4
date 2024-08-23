package com.boot.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.RviewtbDTO;

@Mapper
public interface RviewtbDAO {
	public List<RviewtbDTO> rviewlist(HashMap<String, String> param);
	public int c_rview(RviewtbDTO rviewtbDTO);
	public int getTotalCount(HashMap<String, String> param);
}
