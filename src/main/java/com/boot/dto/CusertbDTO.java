package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CusertbDTO {
	private String cuserid;
	private String cpass;
	private String cusnm;
	private String phone;
	private String cmail;
	private String bsnum;
	private String cleader;
	private String caddr;
	private Timestamp adate;
	private Timestamp mdate;
	
	private int boardcnt;
	private int jobpostcnt;
	private int jobaplycnt;
	private int offercnt;
}