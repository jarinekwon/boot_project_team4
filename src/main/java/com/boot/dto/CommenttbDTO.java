package com.boot.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommenttbDTO {
	private int replyno;
	private String replyid;
	private String content;
	private int boardno;
	private Timestamp adate;
}
