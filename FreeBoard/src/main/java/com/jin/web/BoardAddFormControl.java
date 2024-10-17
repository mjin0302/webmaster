package com.jin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;

public class BoardAddFormControl implements Control {
	
	// addBoardForm.do -> boardForm.jsp : 등록화면 조회
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("WEB-INF/jsp/boardForm.jsp").forward(req, resp);
	}

}
