package com.jin.control.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.common.Control;
import com.jin.service.BoardService;
import com.jin.service.BoardServiceImpl;
import com.jin.vo.BoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardAddControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		
		String savePath = req.getServletContext().getRealPath("images");
		int maxSize = 1024 * 1025 * 5;	// 최대 사이즈
		
		// Multipart요청에 대한 처리로 변경
		MultipartRequest mr = new MultipartRequest(
					req, 		// 1. 요청정보
					savePath,	// 2. 저장경로
					maxSize,	// 3. 최대크기Ø
					"utf-8",	// 4. encoding 방식
					new DefaultFileRenamePolicy()
				);//	5. 리네임 정책
		
		// title, content, writer 3개 파라미터. db 등록 -> 목록 보여주기
		// key=value&key=value text처리
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String memberId = mr.getParameter("memberId");
		String img = mr.getFilesystemName("img");
		
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setMemberId(memberId);
		vo.setImg(img);
		
		BoardService service = new BoardServiceImpl();
		try {
			// 정상적으로 등록되면 등록되었는지 결과를 위해 목록 페이지로 이동
			service.registerBoard(vo);
			resp.sendRedirect("boardList.do");
		} catch (Exception e) {
			// 정상적으로 처리되지 않았을 때 다시 등록화면으로 이동
			req.setAttribute("msg", "등록하는 중 오류가 발생했습니다");
			req.getRequestDispatcher("WEB-INF/jsp/boardForm.jsp").forward(req, resp);
		}
		
		
	}

}
