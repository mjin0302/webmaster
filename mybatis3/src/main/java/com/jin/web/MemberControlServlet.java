package com.jin.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.jin.common.DataSource;
import com.jin.dao.MemberMapper;
import com.jin.vo.Member;

// 제어는 톰캣이 한다 !!!
// 1. 객체생성
// 2. init메소드를 실행
// 3. service()를 실행
// 4. destroy()메소드를 실행

@WebServlet("/member.action")
public class MemberControlServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public MemberControlServlet() {
		System.out.println("Membercontrol 객체 생성");
	}
	
	// 따로 정의를 하지 않으면 부모 메소드가 가지고 있는 서비스를 실행함
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("최초요청이면 init 실행");
	}
	
	// doGet, doPost 메소드 모드 서비스가 가지고 있는 메소드이다
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		System.out.println("서블릿을 요청할 때 마다 실행");
		
		SqlSession session = DataSource.getInstance().openSession(true);
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		// http://localhost/mybatis3/member.action?mid=user01
		String id = req.getParameter("mId"); 
		
		// get : 조회하면, post : 삭제처리.
		// getMethod() = 요청방식을 구분하는 메소드
		if(req.getMethod().equals("GET")) {	// 단건조회.
			
			Member member = mapper.selectMember(id);
			
			if(member == null) {
				resp.getWriter().print("조회된 정보가 없습니다");
				return;
			}
			
			String str = "<h3>회원정보</h3>";
			str += "<form action='member.action' method='post'>";
			str += "<input type='hidden' name='mId' value='" + member.getMemberId() + "'>";
			str += "<table border = '1'>";
			str += "<tr><th>회원아이디</th><td>" + member.getMemberId() + "</td></tr>";
			str += "<tr><th>회원아이디</th><td>" + member.getMemberName() + "</td></tr>";
			str += "<tr><th>회원아이디</th><td>" + member.getPhone() + "</td></tr>";
			str += "<tr><td colspan='2'><input type='submit'></td></tr>";
			str += "</table>";
			str += "</form><br>";
			str += "<a href='MemberListServlet'>목록으로</a>";
			
			resp.getWriter().print(str);
			
		} else if(req.getMethod().equals("POST")) {	// 삭제처리
			if(mapper.deleteMember(id) > 0) {
				resp.getWriter().print("<p>삭제완료</p>");
			} else {
				resp.getWriter().print("<p>삭제 할 회원 없음</p>");
			}
			resp.getWriter().print("<a href='MemberListServlet'>목록으로</a>");
		}
		
		
		
		
	}
	
	@Override
	public void destroy() {
		System.out.println("서버가 종료될 때 한번 실행");
	}
}
