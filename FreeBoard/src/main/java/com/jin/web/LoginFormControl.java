package com.jin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jin.common.Control;
import com.jin.service.MemberService;
import com.jin.service.MemberServiceImpl;
import com.jin.vo.MemberVO;

public class LoginFormControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("uId");
		String pass = req.getParameter("uPass");

		if (req.getMethod().equalsIgnoreCase("GET")) {

			req.getRequestDispatcher("WEB-INF/jsp/logForm.jsp").forward(req, resp);

		} else if (req.getMethod().equalsIgnoreCase("POST")) {

			// 로그인 실행
			MemberService svc = new MemberServiceImpl();
			MemberVO member = svc.loginCheck(id, pass);
			System.out.println("member : " + member);
			if (member == null) {
				// 로그인 실패.
				req.setAttribute("msg", "아이디와 비밀번호를 확인하세요");
				req.getRequestDispatcher("WEB-INF/jsp/loginForm.jsp").forward(req, resp);
				return;
			}

			// 정상 로그인. session 객체
			HttpSession session = req.getSession();
			session.setAttribute("logId", id);
			if (member.getResponsibility().equalsIgnoreCase("User"))
				resp.sendRedirect("boardList.do");
			else if (member.getResponsibility().equalsIgnoreCase("Admin"))
				resp.sendRedirect("memberList.do");

		}

	}

}
