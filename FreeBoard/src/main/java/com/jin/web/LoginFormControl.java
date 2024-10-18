package com.jin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jin.common.Control;
import com.jin.service.MemberService;
import com.jin.service.MemberServiceImpl;

public class LoginFormControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("uId");
		String pass = req.getParameter("uPass");

		if(req.getMethod().equals("GET")) {
			
			req.getRequestDispatcher("WEB-INF/jsp/loginForm.jsp").forward(req, resp);
			
		} else if(req.getMethod().equals("POST")) {
			
			// 로그인 실행
			MemberService svc = new MemberServiceImpl();
			
			if(svc.loginCheck(id, pass) == null) {
				// 로그인 실패.
				req.setAttribute("msg", "아이디와 비밀번호를 확인하세요");
				req.getRequestDispatcher("WEB-INF/jsp/loginForm.jsp").forward(req, resp);
				return;
			}
			
			// 정상 로그인. session 객체
			HttpSession session = req.getSession();
			session.setAttribute("logId", id);
			
			resp.sendRedirect("boardList.do");
		}
		
		
	}

}
