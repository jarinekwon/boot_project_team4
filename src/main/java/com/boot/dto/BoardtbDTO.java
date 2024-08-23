package com.boot.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardtbDTO {
	private int boardno;	
	private String authorid;
	private String title;	
	private String content;	
	private String boardgb;	
	private int boardhit;	
	private Timestamp adate;
	private Timestamp mdate;

	//add columns
	private int rownum;

	//private List<BoardAttachDTO> attachList;
}









