package com.boot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.ShowskilltbDTO;

@Mapper
public interface ShowskilltbDAO {
	public ArrayList<ShowskilltbDTO> selectAll();
}
