package com.jin.service;

import java.util.List;

import com.jin.vo.BoardVO;

public interface BoardService {
	
	List<BoardVO> boardAllList(int page);		// 전체 목록 조회

	BoardVO searchBoard(int boardNo);	// 단건조회

	boolean registerBoard(BoardVO board); 	// 글 등록

	boolean modifyBoard(BoardVO board); 	// 글 수정

	boolean removeBoard(int boardNo); 		// 글 삭제
	
}
