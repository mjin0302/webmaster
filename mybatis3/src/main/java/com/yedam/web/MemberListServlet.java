package com.yedam.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.jin.common.DataSource;
import com.jin.dao.MemberMapper;
import com.jin.vo.Member;

@WebServlet("/MemberListServlet")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberListServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.getWriter();

		PrintWriter out = response.getWriter();
		out.print("<a href='index.html'>첫 페이지로 이동</a>");

		SqlSession session = DataSource.getInstance().openSession(true);

		MemberMapper dao = session.getMapper(MemberMapper.class);

		List<Member> list = new ArrayList<>();
		list = dao.members();

		for (Member member : list) {
			response.getWriter().print(member);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

	}
}
