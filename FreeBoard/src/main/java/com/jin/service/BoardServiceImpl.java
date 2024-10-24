package com.jin.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.jin.common.DataSource;
import com.jin.common.SearchDTO;
import com.jin.mapper.BoardMapper;
import com.jin.vo.BoardVO;


public class BoardServiceImpl implements BoardService {
	
	SqlSession session = DataSource.getInstance().openSession(true);
	BoardMapper mapper = session.getMapper(BoardMapper.class);
	
	// 게시글 전체조회
	@Override
	public List<BoardVO> boardAllList(SearchDTO search) {

		return mapper.listWithPage(search);
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
		
		mapper.updateCount(boardNo); // 조회 & 조회수 증가.
		return mapper.selectBoard(boardNo);
	}

	// 전체데이터 수
	@Override
	public int getTotalCount(SearchDTO search) {
		return mapper.selectCount(search);
	}

	// 사용자별 게시글 작성건수
	@Override
	public List<Map<String, Object>> countByWriter() {
		return mapper.countByWriter();
	}
	
} //BoardServiceImpl()
