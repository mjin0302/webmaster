package com.jin.vo;

import java.util.Date;

import lombok.Data;

@Data // getter, setter, toString, equals, hashCode 등등
public class BoardVO {
	
	 private int 	boardNo;	// 게시글 번호
	 private String title;		// 제목
	 private String content;	// 내용
	 private String memberId;	// 작성자
	 private String name;		// 작성자 이름
	 private String view_cnt;	// 조회수
	 private Date 	writeDate;	// 작성일시
	 private Date 	updateDate;	// 수정일시
	 
}
