package com.boot.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobposttbDTO {
	private String cuserid;
	private int csrno;
	private int jobno;
	private String jobtitle;
	private String jobsubtitle;
	private String content;
	private int prsup;
	private int salary;
	private int educa;
	private String loc01;
	private int wrkty;
	private String wrktm;
	private String position;
	private String skills;
	private String duties;
	private String pfntcd;
	private String wrkcd;
	private String benefits;
	private String scrnpcd;
	private String pamoa;
	private String rcmpcd;
	private int notic;
	private int recno;
	private int supno;
	private Timestamp ddate;
	private Timestamp adate;
	private Timestamp mdate;
	
	//add columns
	private String careernm;
	private String edunm;
	private String wrktynm;
	private int daycha;
	private int rn;
	
	private String cpass;
	private String cusnm;
	private String phone;
	private String cmail;
	private String bsnum;
	private String cleader;
	private String caddr;
	
	private String imgcd;
	private String protitle;
	private String prstitle;
	private String puserid;
	private int prono;
	private int gendr;
	private Timestamp birdy;
	private String email;
	private String paddr;
	private String prsself;
	private int grade;
	private int gradesta;
	private int armgu;
	private String propo;
	private String proself;
	private int prcnt;
	
	private int imgno;
	private String usetb;
	private String gubun;
	private String uuid;
	private String uploadpath;
	private String filename;
}
