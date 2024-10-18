package com.jin.common;

import lombok.Data;

@Data
public class PageDTO {
	
	private int startPage, endPage;
	private boolean prev, next;
	private int page;	// 현재페이지
	
	public PageDTO(int page, int totalCnt) {
		//int totalCnt = 600;
		this.page = page;
		
		this.endPage = (int)Math.ceil(page / 10.0) * 10;
		this.startPage = this.endPage - 9 ;
		
		int realEnd = (int) Math.ceil(totalCnt / 10.0);	// 건수 계산 => 최종 페이지
		this.endPage = this.endPage > realEnd ? realEnd : this.endPage;
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;

	}
	
}
