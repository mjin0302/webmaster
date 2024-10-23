package com.jin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jin.vo.ReplyVO;

public interface ReplyMapper {
	
	List<ReplyVO> selectList(int boardNo);	// 댓글 조회
	
	List<ReplyVO> selectListPaging(@Param("bno") int bno, @Param("page") int page);	// 댓글 조회
	
	int insertReply(ReplyVO reply);	// 댓글 등록

	int deleteReply(int replyNo);	// 댓글 삭제
	
	ReplyVO selectReply(int replyNo);	// 단건 조회
	
	int selectCount(int boardNo);	// 댓글 건수
}
