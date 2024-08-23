package com.boot.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.MusertbDTO;

@Mapper
public interface MusertbDAO {
	public MusertbDTO mloginYn(HashMap<String, String> param);
}
