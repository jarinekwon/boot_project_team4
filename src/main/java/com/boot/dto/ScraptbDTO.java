package com.boot.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ScraptbDTO {
	private String authorid; //스크랩하는 아이디
	private String scrapid; // 스크랩 당하는 아이디
	private int csrno;
	private int scrapno;
	private String usetype;
	private Timestamp adate;
	private Timestamp mdate;
	
	/* add */
	private int rn;
	private String cusnm;
	private String caddr;
	private String jobtitle;
	private Timestamp ddate;  //마감일자
	private String edunm; // 학력구분
	private String careernm; // 경력구분
	private String loc01; // 지역
	private String wrktynm;
	private int daycha;
	private int jobno;
	
	
}