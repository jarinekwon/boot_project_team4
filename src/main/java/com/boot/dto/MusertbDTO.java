package com.boot.dto;

import java.security.Timestamp;

import lombok.Data;

@Data
public class MusertbDTO {
	private String muserid;
	private String mpass;
	private String mname;
	private Timestamp adate;
	private Timestamp mdate;
}