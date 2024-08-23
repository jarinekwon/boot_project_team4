package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ShowskilltbDTO {
	private int skillno;
	private String skillnm;
	private Timestamp adate;
	private Timestamp mdate;
}