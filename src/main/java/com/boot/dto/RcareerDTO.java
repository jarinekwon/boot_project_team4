package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RcareerDTO {
	private String puserid;
	private int prono;
	private int corpno;
	private String corpnm;
	private Timestamp sdate;
	private Timestamp edate;
	private int wrkty;
	private String position;
	private String task;
	private Timestamp adate;
	private Timestamp mdate;
}
