package com.jin.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jin.common.DataSource;
import com.jin.mapper.BoardMapper;
import com.jin.vo.BoardVO;

public class AppTest {

	public static void main(String[] args) {

		SqlSession session = DataSource.getInstance().openSession();

		BoardMapper mapper = session.getMapper(BoardMapper.class);

		BoardVO bvo = new BoardVO();

//		bvo.setTitle("mapperTest");
//		bvo.setContent("dddddddddddddddddddddddddd");
//		bvo.setBoardNo(5);
		
		
//		if(mapper.selectBoard(5) == null) {
//			System.out.println("조회된 내용이 없습니다.");
//		}
//
//		List<BoardVO> list = mapper.boardAllList();
//		for (BoardVO board : list) {
//			System.out.println(board.toString());
//
//		}
		
		List<BoardVO> list = mapper.listWithPage(3);
		
		for (BoardVO board : list) {
			System.out.println(board.toString());
		}

	}
}
