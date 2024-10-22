package com.jin.service;

import java.util.List;

import com.jin.vo.ReplyVO;

public interface ReplyService {
	List<ReplyVO> replyList(int boardNo);	// 댓글 조회
	
	boolean addReply(ReplyVO reply);	// 댓글 등록
		
	boolean removeReply(int replyNo);	// 댓글 삭제
	
	ReplyVO getReply(int replyNo);	// 단건 조회
}
