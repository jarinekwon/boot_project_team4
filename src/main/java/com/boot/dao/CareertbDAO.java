package com.boot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.CareertbDTO;

@Mapper
public interface CareertbDAO {
	public ArrayList<CareertbDTO> selectAll();
}
