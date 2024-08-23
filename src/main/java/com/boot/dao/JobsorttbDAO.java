package com.boot.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.JobsorttbDTO;

@Mapper
public interface JobsorttbDAO {
	public ArrayList<JobsorttbDTO> jobsortAll();
}