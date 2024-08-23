package com.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Criteria {
	private int pageNum; //페이지 번호
	private int amount; //페이지당 글 갯수
	
	private String type;
	private String keyword;
	
	private String sort;
	private String order;
	private int offset;
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}