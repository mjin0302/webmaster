package com.jin.service;

import java.util.List;
import java.util.Map;

import com.jin.common.SearchDTO;
import com.jin.vo.BoardVO;

public interface BoardService {

	List<BoardVO> boardAllList(SearchDTO search); // 전체 목록 조회

	BoardVO searchBoard(int boardNo); // 단건조회

	boolean registerBoard(BoardVO board); // 글 등록

	boolean modifyBoard(BoardVO board); // 글 수정

	boolean removeBoard(int boardNo); // 글 삭제

	int getTotalCount(SearchDTO search); // 페이징 카운트

	// 사용자별 게시글 작성건수
	List<Map<String, Object>> countByWriter();

}
