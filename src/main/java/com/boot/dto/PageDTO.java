package com.boot.dto;

import lombok.Getter;
import lombok.ToString;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@ToString 
//@ToString 어노테이션으로 다음과 같이 출력
//PageDTO(startpage=1, endpage=10, prev=false, next=true, total=123, cri=Criteria(pageNum=1, amount=10))
public class PageDTO {
	private int startpage; //시작 페이지: 1, 11, 21...
	private int endpage; //끝 페이지: 10,20,30....
	private boolean prev, next;
	private int total;
	private Criteria cri; //화면에 출력 갯수
	
	public PageDTO(int total, Criteria cri) {
		this.total = total;
		this.cri = cri;
		
		// 3 / 10 = 0.3(올림 처리) -> 1 * 10(끝 페이지 10);
		// 11 / 10 = 1.1(올림 처리) -> 2 * 10(끝 페이지 20);
		this.endpage = (int)Math.ceil(cri.getPageNum() / 10.0) * 10;
		this.startpage = this.endpage - 9;

		//ex) total: 70, 현재페이지: 3 -> endPage: 10 => 70*1.0/10 => 7페이지
		//ex) total: 300, 현재페이지: 3 -> endPage: 10 => 300*1.0/10 => 30페이지
		int realEnd = (int)Math.ceil((total * 1.0) / cri.getAmount());
		
		if(realEnd <= this.endpage) {
			this.endpage = realEnd;
		}
		
		this.prev = this.startpage > 1; //1페이지보다 크면 존재 -> true/false로 구분
		this.next = this.endpage < realEnd; //endpage가 realEnd 값보다 작을 때
	}
}