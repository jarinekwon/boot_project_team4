package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class EdugbtbDTO {
	private int eduno;
	private String edunm;
	private Timestamp adate;
	private Timestamp mdate;
}