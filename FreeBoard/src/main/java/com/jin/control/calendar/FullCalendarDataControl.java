package com.jin.control.calendar;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jin.common.Control;
import com.jin.service.FullCalendarService;
import com.jin.service.FullCalendarServiceImpl;

public class FullCalendarDataControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/json;charset=utf-8");
		
		FullCalendarService service = new FullCalendarServiceImpl();
		
		List<Map<String, Object>> result = service.fullCalendarAllList();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(result);
		
		resp.getWriter().print(json);
		
	}

}
