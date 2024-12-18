package com.jin.control.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;
import com.jin.service.BoardService;
import com.jin.service.BoardServiceImpl;
import com.jin.vo.BoardVO;

public class BoardUpdateControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		// GET: 수정화면, POST: 수정처리
		String bno = req.getParameter("bno");	// 게시글 번호
		String page = req.getParameter("page");	// 페이지 번호
		
		String sc = req.getParameter("searchCondition"); // 검색
		String kw = req.getParameter("keyword");		 // 키워드
		
		req.setCharacterEncoding("utf-8");
		
		BoardService service = new BoardServiceImpl();
		
		if(req.getMethod().equals("GET")) {
			
			BoardVO board = service.searchBoard(Integer.parseInt(bno));
			
			req.setAttribute("boardvo", board);
			req.setAttribute("page", page);
			req.setAttribute("searchCondition", sc);
			req.setAttribute("keyword", kw);
			
			req.getRequestDispatcher("WEB-INF/jsp/boardUpdateForm.jsp").forward(req, resp);
			
		} else if(req.getMethod().equals("POST")) {
			
			bno = req.getParameter("bno");
			String title = req.getParameter("title");
			String content = req.getParameter("content");

			BoardVO board = new BoardVO();
			
			board.setBoardNo(Integer.parseInt(bno));
			board.setTitle(title);
			board.setContent(content);
			
			if(service.modifyBoard(board)) {
				// 정상처리 -> 목록으로 이동	
				resp.sendRedirect("boardList.do?page=" + page + "&searchCondition=" + sc + "&keyword=" + kw);
			} else {
				board = service.searchBoard(Integer.parseInt(bno));
				
				req.setAttribute("boardvo", board);
				req.setAttribute("msg", "수정 할 게시글이 없습니다.");
				req.getRequestDispatcher("WEB-INF/jsp/boardUpdateForm.jsp").forward(req, resp);
				
			}
			
			
		}
		
	}

}
