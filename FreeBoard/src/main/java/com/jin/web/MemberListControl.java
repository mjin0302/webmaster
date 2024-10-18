package com.jin.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;
import com.jin.service.MemberService;
import com.jin.service.MemberServiceImpl;
import com.jin.vo.MemberVO;

public class MemberListControl implements Control {

	// Control의 구현 클래스
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
		
		req.setCharacterEncoding("utf-8");
		
		System.out.println("req : " + req + "resp : " + resp);
		System.out.println("MemberListControl");
		
		MemberService service = new MemberServiceImpl();
		
		List<MemberVO> list = service.memberList();
		
		// 요청한 값
		req.setAttribute("memberList", list);
		
		
		try {
			// memberList.do로 요청된 페이지에서 memberList.jsp로 요청 재지정
			// forward() 경로를 재지정 하겠다
			// 서버에서 받아 온 값을 클라이언트가 볼 수 있게 jsp파일로 경로를 재지정해서 넘긴다
			// 그래서 jsp에서 화면단을 구현해서 클라이언트가 실질적으로 볼 수 있게 해주는것
			req.getRequestDispatcher("WEB-INF/jsp/memberList.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
