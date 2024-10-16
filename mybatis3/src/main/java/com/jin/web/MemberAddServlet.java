package com.jin.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.jin.common.DataSource;
import com.jin.dao.MemberMapper;
import com.jin.vo.Member;

// 서블릿 실행하기 위한 url.
// 응답 정보를 전송.
// http 프로토콜을 데이터 전송 수신.
// HttpServlet 상속 기능 구현
@WebServlet("/MemberAddservlet")
public class MemberAddServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public MemberAddServlet() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// 자바 => 데이터의 입출력 : 스트림
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print("<h3>여기는 웹 브라우저</h3>");
		out.print("<h3>응답정보를 처리하는 객체 : response</h3>");
		out.print("<a href='index.html'>첫 페이지로 이동</a>");
	}
	
	// request가 파라미터 정보를 알고있음
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글로 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 4개 파라미터
		String id = request.getParameter("uId");
		String pass = request.getParameter("uPass");
		String name = request.getParameter("uName");
		String phone = request.getParameter("uPhone");
		
		Member member = new Member();
		
		member.setMemberId(id);
		member.setMemberName(name);
		member.setPassword(pass);
		member.setPhone(phone);
		
		System.out.println();
		
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		MemberMapper dao = sqlSession.getMapper(MemberMapper.class);
		try {
			if(dao.insertMember(member) == 1) {
				response.getWriter().print("OK");
			}
		} catch (Exception e) {
			response.getWriter().print("NG");
		}
		
	}
	
}
