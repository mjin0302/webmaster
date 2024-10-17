package com.jin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;

public class MemberAddFormControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// jsp 페이지 open.
		req.getRequestDispatcher("WEB-INF/jsp/memberAddForm.jsp").forward(req, resp);
	}

}
