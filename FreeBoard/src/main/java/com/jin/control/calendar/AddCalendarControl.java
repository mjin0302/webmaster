package com.jin.control.calendar;

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

public class AddCalendarControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");
		
		String title = req.getParameter("title");
		String startDt = req.getParameter("start");
		String endDt = req.getParameter("end");
		
		CalendarVO vo = new CalendarVO();
		vo.setTitle(title);
		vo.setStartDate(startDt);
		vo.setEndDate(endDt);
		
		Map<String, Object> result = new HashMap<>();
		FullCalendarService service = new FullCalendarServiceImpl();
		
		try {
			service.addEvent(vo);
			result.put("retCode", "OK");
			result.put("retVal", vo);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("retCode", "FAIL");
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		resp.getWriter().print(gson.toJson(result));
		
	}

}
