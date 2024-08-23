package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ImgtbDTO {
	private int imgno;
	private String usetb;
	private String gubun;
	private String uuid;
	private String uploadpath;
	private String filename;
	private Timestamp adate;
	private Timestamp mdate;
}