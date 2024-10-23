package com.jin.control.reply;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jin.common.Control;
import com.jin.service.ReplyService;
import com.jin.service.ReplyServiceImpl;
import com.jin.vo.ReplyVO;

public class AddReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/json;charset=utf-8");
		
		// parameter: bno, reply, memberId
		String bno = req.getParameter("bno");	// 글 번호
		String reply = req.getParameter("reply");	// 댓글 내용
		String memberId = req.getParameter("memberId");	// 댓글 작성자
		
		ReplyService service = new ReplyServiceImpl();
		
		ReplyVO rVO = new ReplyVO();
		rVO.setBoardNo(Integer.parseInt(bno));
		rVO.setReply(reply);
		rVO.setMemberId(memberId);
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			// {retCode:OK, retVal: rvo}
			service.addReply(rVO);
			result.put("retCode", "OK");
			result.put("retVal", rVO);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("retCode", "FAIL");
			//result.put("retVal", rVO);
		}
		
		Gson gson = new GsonBuilder().create();
		resp.getWriter().print(gson.toJson(result));
		
	}

}
