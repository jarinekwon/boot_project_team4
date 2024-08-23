package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class WrktygbtbDTO {
	private int wrktyno;
	private String wrktynm;
	private Timestamp adate;
	private Timestamp mdate;
}