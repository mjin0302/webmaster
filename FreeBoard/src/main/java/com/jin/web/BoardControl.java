package com.jin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;
import com.jin.service.BoardService;
import com.jin.service.BoardServiceImpl;
import com.jin.vo.BoardVO;

public class BoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		// board.do 요청이 들어오면 -> 상세조회(bno) : service.selectBoard(bno) -> 조회 -> board.jsp 출력.
		
		String bno = req.getParameter("bno");
		String page = req.getParameter("page");
		
		String sc = req.getParameter("searchCondition"); // 검색
		String kw = req.getParameter("keyword");		 // 키워드
		
		BoardService service = new BoardServiceImpl();
		BoardVO board = service.searchBoard(Integer.parseInt(bno));
		
		req.setAttribute("boardvo", board);
		
		req.setAttribute("page", page);
		
		req.setAttribute("searchCondition", sc);
		req.setAttribute("keyword", kw);
		
		req.getRequestDispatcher("WEB-INF/jsp/board.jsp").forward(req, resp);
	}

}
