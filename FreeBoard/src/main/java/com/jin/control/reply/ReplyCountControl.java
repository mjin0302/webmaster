package com.jin.control.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;
import com.jin.service.ReplyService;
import com.jin.service.ReplyServiceImpl;

public class ReplyCountControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bno = req.getParameter("bno");
		
		ReplyService service = new ReplyServiceImpl();
		int totalCnt = service.replyCount(Integer.parseInt(bno));
		
		// {"totalCnt" : 10}
		resp.getWriter().print("{\"totalCnt\" : " + totalCnt + "}");
	}

}
