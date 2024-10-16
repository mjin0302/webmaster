package com.jin.web;

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

// Servlet컨테이너만(톰캣) Servlet을 실행할 수 있음
// ioc 제어의 역전 -> 제어를 개발자가 하는것이 아니라 톰캣이 제어를 함 그래서 컨테이너가 가지고 있는 규칙에 맞춰서 구현을 해야한
// 객체 생성 -> init()을 처음 실행함! -> service()메소드를 실행 -> destroy()메소드 실행: 서블릿 생명주기 

@WebServlet("/MemberListServlet")	// 이 url로 요청이 들어오면 해당 클래스가 실행이 된다.
public class MemberListServlet extends HttpServlet {	// Servlet을 만들려면 HttpServlet를 상속받아 만들어야한다
	private static final long serialVersionUID = 1L;

	public MemberListServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// byte, short, int, char => r객체(seri
		response.setContentType("text/html;charset=utf-8");
		response.getWriter();

		PrintWriter out = response.getWriter();

		SqlSession session = DataSource.getInstance().openSession(true);
		MemberMapper dao = session.getMapper(MemberMapper.class);

		List<Member> list = new ArrayList<>();
		list = dao.members();

		String str = "<h3>회원정보</h3>";
		str += "<table border = '1'>";
		str += "<thead><tr><th>회원아이디</th><th>회원명</th><th>연락처</th></tr></thead>";
		str += "<tbody>";
		for (Member member : list) {
			str += "<tr>"
					+ "<td>"
						+ "<a href='member.action?mId=" + member.getMemberId() +"'>"
							+ member.getMemberId() + "</a>"
						+ "</td>"
					+ "<td>" + member.getMemberName() + "</td>"
					+ "<td>" + member.getPhone() + "</td>"
				+ "</tr>";
		}
		str += "</tbody>";
		str += "</table><br>";
		str += "<a href='./'>첫 페이지</a>";
		out.print(str);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

	}
}
