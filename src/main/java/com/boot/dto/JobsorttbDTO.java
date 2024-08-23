package com.boot.dto;

import java.security.Timestamp;

import lombok.Data;

@Data
public class JobsorttbDTO {
	private int sortno;
	private String sortnm;
	private Timestamp adate;
	private Timestamp mdate;
}