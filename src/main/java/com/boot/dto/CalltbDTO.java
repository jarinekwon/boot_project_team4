package com.boot.dto;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class CalltbDTO {
	private int callno;
	private String authorid;
	private String calltitle;
	private String callcontent;
	private String callyn;
	private String callreply;
	private Timestamp adate;
	private Timestamp mdate;
	
	/* add */
	private int rn;
}