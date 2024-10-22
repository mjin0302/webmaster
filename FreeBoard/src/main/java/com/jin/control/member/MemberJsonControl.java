package com.jin.control.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;
import com.jin.service.MemberService;
import com.jin.service.MemberServiceImpl;
import com.jin.vo.MemberVO;

public class MemberJsonControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 회원목록을 json형태로 반환함
		resp.setContentType("text/json;charset=utf-8");
		
		
		MemberService service = new MemberServiceImpl();
		List<MemberVO> list = service.memberList();
		
		//[{"memberId": "user", "memberName":"사용자1", ...},
		// {.....},
		// {.....}]
		// "member"
		String json = "[";
		for(int i = 0; i < list.size(); i++) {
			json += "{\"memberId\":\"" + list.get(i).getMemberId()+"\", \"memberName\": \"" + list.get(i).getMemberName() + "\", \"memberPhone\": \"" + list.get(i).getPhone() + "\" }";
			if(i != list.size()-1) {
				json += ",";
			}
		}
		json += "]";
		resp.getWriter().print(json);
		
	}

}
