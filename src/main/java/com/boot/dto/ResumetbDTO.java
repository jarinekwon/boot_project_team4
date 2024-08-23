package com.boot.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class ResumetbDTO {
	private String puserid;
	private int prono;
	private String protitle;
	private String prstitle;
	private String pname;
	private int gender;	
	private Date birdy;	
	private String imgcd;
	private String email;
	private String phone;
	private String paddr;
	private int prsup;	
	private String prsself;
	private int classgb;
	private String shoolnm;
	private int grade;
	private int gradesta;
	private String majornm;
	private double credit;
	private int score;
	private Date sdate;
	private Date edate;
	private int salary;
	private int armgu;
	private String propo;
	private String proself;
	private int prcnt;
	private Timestamp adate;
	private Timestamp mdate;
	
	//add 컬럼
	private int imgno;
	private String usetb;
	private String gubun;
	private String uuid; 
	private String uploadpath;
	
	private int rn;
	private boolean representative;
	
	private String job;
	
	private String skillnos;
	private String skillnms;
	
	private int cnt;
}