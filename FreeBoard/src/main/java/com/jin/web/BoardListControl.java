package com.jin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;
import com.jin.common.PageDTO;
import com.jin.common.SearchDTO;
import com.jin.service.BoardService;
import com.jin.service.BoardServiceImpl;
import com.jin.vo.BoardVO;

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String page = req.getParameter("page");
		page = page == null ? "1" : page;
		
		// 검색
		String sc = req.getParameter("searchCondition");
		String kw = req.getParameter("keyword");
		
		// 검색 조건
		SearchDTO search = new SearchDTO();
		search.setKeyword(kw);
		search.setSearchCondition(sc);
		search.setPage(Integer.parseInt(page));
		
		// 글 목록 보여주기. 조회 후 jsp전달
		BoardService service = new BoardServiceImpl();
		// 게시글 조회
		List<BoardVO> list = service.boardAllList(search);
		int totalCount = service.getTotalCount(search); // 전체 데이터 수
		
		// 서버에서 응답받은 값을 req<boardList, list> 형태
		req.setAttribute("boardList", list);
		req.setAttribute("page", new PageDTO(Integer.parseInt(page), totalCount));
		req.setAttribute("searchCondition", sc);
		req.setAttribute("keyword", kw);
		
		// jsp 페이지
		req.getRequestDispatcher("WEB-INF/jsp/boardList.jsp").forward(req, resp);
		
	}
	
	
}
