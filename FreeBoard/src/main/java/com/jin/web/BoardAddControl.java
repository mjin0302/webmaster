package com.jin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;
import com.jin.service.BoardService;
import com.jin.service.BoardServiceImpl;
import com.jin.vo.BoardVO;

public class BoardAddControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String memberId = req.getParameter("memberId");
		
		BoardVO vo = new BoardVO();
		
		vo.setTitle(title);
		vo.setContent(content);
		vo.setMemberId(memberId);
		
		BoardService service = new BoardServiceImpl();
		try {
			// 정상적으로 등록되면 등록되었는지 결과를 위해 목록 페이지로 이동
			service.registerBoard(vo);
			resp.sendRedirect("boardList.do");
		} catch (Exception e) {
			// 정상적으로 처리되지 않았을 때 다시 등록화면으로 이동
			req.setAttribute("msg", "등록하는 중 오류가 발생했습니다");
			req.getRequestDispatcher("WEB-INF/jsp/boardForm.jsp").forward(req, resp);
		}
		
		
	}

}
