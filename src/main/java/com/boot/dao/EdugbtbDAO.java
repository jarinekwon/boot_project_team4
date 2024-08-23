package com.boot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.EdugbtbDTO;

@Mapper
public interface EdugbtbDAO {
	public ArrayList<EdugbtbDTO> selectAll();
}
