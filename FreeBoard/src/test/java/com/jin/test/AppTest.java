package com.jin.test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jin.common.DataSource;
import com.jin.common.SearchDTO;
import com.jin.mapper.BoardMapper;
import com.jin.mapper.ReplyMapper;
import com.jin.service.BoardService;
import com.jin.service.BoardServiceImpl;
import com.jin.service.FullCalendarService;
import com.jin.service.FullCalendarServiceImpl;
import com.jin.service.ReplyService;
import com.jin.service.ReplyServiceImpl;
import com.jin.vo.BoardVO;
import com.jin.vo.CalendarVO;
import com.jin.vo.ReplyVO;

public class AppTest {

	public static void main(String[] args) {

		SqlSession session = DataSource.getInstance().openSession();

		BoardMapper mapper = session.getMapper(BoardMapper.class);
		ReplyMapper rMapper = session.getMapper(ReplyMapper.class);

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
		
		SearchDTO search = new SearchDTO();
		search.setKeyword("점심");
		search.setSearchCondition("T");
		search.setPage(2);
		
//		for (BoardVO board : search) {
//			System.out.println(board.toString());
//		}
		
//		ReplyService service = new ReplyServiceImpl();
//		
//		ReplyVO reply = new ReplyVO();
//		reply.setBoardNo(585);
//		reply.setReply("댓글 테스트");
//		reply.setMemberId("ongsim");
//		
//		service.addReply(reply);
//		
//		service.removeReply(reply.getReplyNo());
//		
//		List<ReplyVO> list = rMapper.selectList(595);
//		for(ReplyVO rep : list) {
//			System.out.println(rep.toString());
//		}
	}
}
