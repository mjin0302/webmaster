package com.jin.control.reply;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jin.common.Control;
import com.jin.service.ReplyService;
import com.jin.service.ReplyServiceImpl;
import com.jin.vo.ReplyVO;

public class ReplyListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시글 번호(bno)
		resp.setContentType("text/json;charset=utf-8");
		String bno = req.getParameter("bno");
		String page = req.getParameter("page");
		
		ReplyService service = new ReplyServiceImpl();
		List<ReplyVO> list = service.replyList(Integer.parseInt(bno), Integer.parseInt(page));
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(list); // 자바객체 -> json문자열로 변경해줌
				
		resp.getWriter().print(json);
	}

}
