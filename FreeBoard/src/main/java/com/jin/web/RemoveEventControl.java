package com.jin.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jin.common.Control;
import com.jin.service.FullCalendarService;
import com.jin.service.FullCalendarServiceImpl;
import com.jin.vo.CalendarVO;

public class RemoveEventControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");
		
		String title = req.getParameter("title");
		String start = req.getParameter("start");
		String end = req.getParameter("end");

		CalendarVO vo = new CalendarVO();
		vo.setTitle(title);
		vo.setStartDate(start);
		vo.setEndDate(end);
		
		System.out.println("dsfasdfsdafsdfasdfsdfdsafdasfsdfdasffsdfsd  " + vo);
		
		FullCalendarService service = new FullCalendarServiceImpl();
		Map<String, Object> result = new HashMap<>();
		
		if(service.removeEvent(vo)) {
			result.put("retCode", "OK");
			result.put("retVal", vo);
			
		} else {
			result.put("retCode", "FAIL");
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		resp.getWriter().print(gson.toJson(result));
		
	}

}
