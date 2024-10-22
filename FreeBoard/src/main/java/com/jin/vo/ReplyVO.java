package com.jin.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	private int replyNo;
	private String reply;
	private String memberId;
	private int boardNo;
	private Date replyDate;
	
}
