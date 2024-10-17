package com.jin.mapper;

import java.util.List;

import com.jin.vo.BoardVO;

public interface BoardMapper {

	List<BoardVO> boardAllList();		// 전체 목록 조회

	BoardVO selectBoard(int boardNo);	// 단건조회

	int insertBoard(BoardVO board); 	// 글 등록

	int updateBoard(BoardVO board); 	// 글 수정

	int deleteBoard(int boardNo); 		// 글 삭제
	
	int updateCount(int boardNo);		// 조회수 증가
	
	List<BoardVO> listWithPage(int page);
}
