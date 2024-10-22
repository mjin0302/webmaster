package com.jin.control.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;
import com.jin.service.MemberService;
import com.jin.service.MemberServiceImpl;

public class DelMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 앞단에서 보내준 id값으로 데이터 지우기
		
		String id  = req.getParameter("id");
		
		MemberService service = new MemberServiceImpl();
		if(service.retireMember(id)) {
			resp.getWriter().print("{\"retCode\": \"OK\"}");
		} else {
			resp.getWriter().print("{\"retCode\": \"FAIL\"}");
		}

	}

}
