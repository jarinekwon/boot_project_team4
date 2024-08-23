package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CareertbDTO {
	private int careerno;
	private String careernm;
	private Timestamp adate;
	private Timestamp mdate;
}
