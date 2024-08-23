package com.boot.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.SectortbDTO;

//실행시 매퍼파일을 읽어 들이도록 지정
@Mapper
public interface SectortbDAO {
	public ArrayList<SectortbDTO> Sectortblist();
}