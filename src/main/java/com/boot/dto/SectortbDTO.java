package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SectortbDTO {
	private int sequeno;		//순번
	private int deptno;			//업종코드
	private String deptnm;		//업종명
	private Timestamp adate;	//등록 일자
	private Timestamp mdate;	//등록 일자
}