package com.jin.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;
import com.jin.service.MemberService;
import com.jin.service.MemberServiceImpl;
import com.jin.vo.MemberVO;

// 화면을 보여주는 컨트롤
public class MemberAddControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			System.out.println("MemberAddControl");
			
			String id = req.getParameter("mid");
			String name = req.getParameter("mname");
			String passwd = req.getParameter("passwd");
			String phone = req.getParameter("phone");
			
			MemberVO mvo = new MemberVO();
			mvo.setMemberId(id);
			mvo.setMemberName(name);
			mvo.setPassword(passwd);
			mvo.setPhone(phone);
			
			MemberService svc = new MemberServiceImpl();
			if(svc.addMember(mvo)) {
				// 목록페이지로 이동
				resp.sendRedirect("memberList.do");	// 얘는 그저 페이지만 열어줌 매가값으로 화면단으로 값을 넘겨주지는 못함
			} else {
				// 사용자 등록화면으로 이동
				resp.sendRedirect("memberAddForm.do");
			}
	}
	
}
