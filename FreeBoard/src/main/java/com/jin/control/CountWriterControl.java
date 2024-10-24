package com.jin.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jin.common.Control;
import com.jin.service.BoardService;
import com.jin.service.BoardServiceImpl;

public class CountWriterControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 차트 데이터 처리하는 컨트롤러
		resp.setContentType("text/json;charset=utf-8");
		
		BoardService service = new BoardServiceImpl();
		List<Map<String, Object>> result = service.countByWriter();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(result);
		
		resp.getWriter().print(json);
	}

}
