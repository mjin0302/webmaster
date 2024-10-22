package com.jin.control.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;
import com.jin.service.MemberService;
import com.jin.service.MemberServiceImpl;
import com.jin.vo.MemberVO;

public class AddMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 앞단에서 던져준 값 처리 id, name, phone, pass
		// 클라이언트가 보내준 정보를 DB에 저장하는 작업!!
		
		MemberVO mem = new MemberVO();
		mem.setMemberId(req.getParameter("id"));
		mem.setPassword(req.getParameter("pass"));
		mem.setPhone(req.getParameter("phone"));
		mem.setMemberName(req.getParameter("name"));
		
		MemberService service = new MemberServiceImpl();
		try {
			service.addMember(mem);
			// {"retCode": "OK"}
			resp.getWriter().print("{\"retCode\": \"OK\"}");
		} catch (Exception e) {
			// {"retCode": "FAIL"}
			resp.getWriter().print("{\"retCode\": \"FAIL\"}");
		}
	}

}
