package com.jin.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jin.common.DataSource;
import com.jin.mapper.BoardMapper;
import com.jin.vo.BoardVO;


public class BoardServiceImpl implements BoardService {
	
	SqlSession session = DataSource.getInstance().openSession(true);
	BoardMapper mapper = session.getMapper(BoardMapper.class);
	
	// 게시글 전체조회
	@Override
	public List<BoardVO> boardAllList(int page) {

		return mapper.listWithPage(page);
	}
	
	// 게시글 등록
	@Override
	public boolean registerBoard(BoardVO board) {
		
		return mapper.insertBoard(board) == 1;
	}
	
	// 게시글 수정
	@Override
	public boolean modifyBoard(BoardVO board) {
		
		return mapper.updateBoard(board) == 1;
	}
	
	// 게시글 삭제
	@Override
	public boolean removeBoard(int boardNo) {
		
		return mapper.deleteBoard(boardNo) == 1;
	}
	
	// 단건조회
	@Override
	public BoardVO searchBoard(int boardNo) {
		
		mapper.updateCount(boardNo); // TODO 조회 & 조회수 증가.
		return mapper.selectBoard(boardNo);
	}
	
} //BoardServiceImpl()
