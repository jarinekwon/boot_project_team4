package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SkilltbDTO {
	private String puserid;
	private int prono;
	private int sequeno;
	private String skillname;
	private Timestamp adate;
	private Timestamp mdate;
}
