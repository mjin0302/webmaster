package com.jin.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Control {
	
	// 메소드
	void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	
}
