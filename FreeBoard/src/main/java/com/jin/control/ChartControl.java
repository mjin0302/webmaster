package com.jin.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;

public class ChartControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// WEB-INF/jsp/etc/chart.jsp
		// 차트 페이지 보여주는 컨트롤
		req.getRequestDispatcher("etc/chart.tiles").forward(req, resp);
		
		
		
	}

}
