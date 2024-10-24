package com.jin.mapper;

import java.util.List;
import java.util.Map;

import com.jin.common.SearchDTO;
import com.jin.vo.BoardVO;

public interface BoardMapper {

	List<BoardVO> boardAllList(); // 전체 목록 조회

	BoardVO selectBoard(int boardNo); // 단건조회

	int insertBoard(BoardVO board); // 글 등록

	int updateBoard(BoardVO board); // 글 수정

	int deleteBoard(int boardNo); // 글 삭제

	int updateCount(int boardNo); // 조회수 증가

	List<BoardVO> listWithPage(SearchDTO search); // 전체조회 및 페이징 및 검색

	int selectCount(SearchDTO search); // 페이징 계산 건수체크

	// 사용자별 게시글 작성건수
	List<Map<String, Object>> countByWriter();
}
