package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RviewtbDTO {
	private int viewno;
	private String puserid;
	private String cuserid;
	private int prono;
	private Timestamp adate;
	private Timestamp mdate;
	
	private int jobno;
	private String cusnm;
}
