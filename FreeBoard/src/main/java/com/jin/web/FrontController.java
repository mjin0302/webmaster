package com.jin.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;
import com.jin.control.ChartControl;
import com.jin.control.CountWriterControl;
import com.jin.control.JavaScriptCont;
import com.jin.control.board.BoardAddControl;
import com.jin.control.board.BoardAddFormControl;
import com.jin.control.board.BoardControl;
import com.jin.control.board.BoardDeleteControl;
import com.jin.control.board.BoardListControl;
import com.jin.control.board.BoardUpdateControl;
import com.jin.control.calendar.AddCalendarControl;
import com.jin.control.calendar.FullCalendarControl;
import com.jin.control.calendar.FullCalendarDataControl;
import com.jin.control.member.AddMemberControl;
import com.jin.control.member.DelMemberControl;
import com.jin.control.member.LogOutControl;
import com.jin.control.member.LoginFormControl;
import com.jin.control.member.MemberAddControl;
import com.jin.control.member.MemberAddFormControl;
import com.jin.control.member.MemberJsonControl;
import com.jin.control.member.MemberListControl;
import com.jin.control.reply.AddReplyControl;
import com.jin.control.reply.RemoveReplyControl;
import com.jin.control.reply.ReplyCountControl;
import com.jin.control.reply.ReplyListControl;

//@WebServlet("*.do") // .do로 끝나는 파일들은 해당 서비스를 실행한다.
public class FrontController extends HttpServlet{
	
	Map<String, Control> map;
	
	public FrontController() {
//		System.out.println("객체 생성");
		
		map = new HashMap<>();
	}
	
	// 첫번째 실행
	@Override
	public void init(ServletConfig config) throws ServletException {
		//            key      ,      value
		// map = </memberAdd.do, MemberAddControl()>
		map.put("/memberList.do", new MemberListControl());	// memberList.do 요청이 들어오면 MemberListControl을 실행 할꺼다
		
		// 회원등록 1) 등록화면 2) 등록처리
		map.put("/memberAddForm.do", new MemberAddFormControl()); // 등록 화면(테이블형태의 폼)만 출력하는 컨트롤
		map.put("/memberAdd.do", new MemberAddControl()); // memberAddForm(등록화면)에서 호출한 경로 -> 즉 값을 처리하는 컨트롤
		
		// 게시판 관련
		// 게시글 조회
		map.put("/boardList.do", new BoardListControl());	// 게시글 조회
		map.put("/board.do", new BoardControl());	// 상세조회

		// 글 등록 (등록화면 -> 등록처리)
		map.put("/booardAddForm.do", new BoardAddFormControl()); // 게시글 등록화면 조회
		map.put("/boardAdd.do", new BoardAddControl());	// 게시글 등록서비스
		
		// 글 수정 (수정화면 -> 변경처리)
		// map.put("/boardUpdateForm.do", new BoardUpdateFormControl()); // 게시글 수정
		map.put("/boardUpdate.do", new BoardUpdateControl()); // 게시글 수정
		
		// 글 삭제
		map.put("/boardDelete.do", new BoardDeleteControl()); // 게시글 삭제
		
		map.put("/loginForm.do", new LoginFormControl());
		map.put("/loginOut.do", new LogOutControl());
		
		map.put("/javascript.do", new JavaScriptCont());
		
		// json관련
		map.put("/memberJson.do", new MemberJsonControl());	// 멤버 목록 조회
		map.put("/addMemberJson.do", new AddMemberControl());	// 멤버 등록
		map.put("/removeMemberJson.do", new DelMemberControl());
	
		// 댓글관련(gson라이브러리 사용함)
		map.put("/replyList.do", new ReplyListControl());
		map.put("/removeReply.do", new RemoveReplyControl());
		map.put("/addReply.do", new AddReplyControl());
		map.put("/replyCount.do", new ReplyCountControl());
	
		// chart
		map.put("/chart.do", new ChartControl());
		map.put("/countByWriter.do", new CountWriterControl());
	
		// fullCalendar
		// 조회
		map.put("/fullCalendar.do", new FullCalendarControl());	// 화면조회
		map.put("/fullCalendarData.do", new FullCalendarDataControl());	// 정보 불러오기
		
		// 등록
		map.put("/addEvent.do", new AddCalendarControl());
		map.put("/removeEvent.do", new RemoveEventControl());
		
		
		
		
	
	}
	
	// front controller
	// 두번째 실행
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("서비스 호출");
		
		// 요청페이지?
		// http://localhost/FreeBoard/memberAdd.do -> URL
		// FreeBoard/memberAdd.do -> URI
		// url에 페이지를 요청하면 uri리소스
		String uri = req.getRequestURI(); 	// /FreeBoard/memberAdd.do -> URI값
		String context = req.getContextPath();	// /FreeBoard -> context값
		String page = uri.substring(context.length());	// context로 서브 스트링하면 결과는 memberAdd.do가 들어옴
		
		// page = memberAdd.do
		// map.get(page) -> page가 키 값 -> (map.get(memberAdd))
		// map.get(page)를 하면 init메소드에서 put한 MemberListControl()을 control에 Control의 인터페이스로 저장된다
		Control control = map.get(page); 
		// MemberListControl()은 Control의 구현 클래스라서 exec()메소드를 실행 할 수 있다
		control.exec(req, resp);	// control은 반드시 exec() 메소드를 가지고 있음 왜냐? control인터페이스가 가지고 있는 메소드라서!!
		
	}
}
