<%@page import="com.jin.common.PageDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.jin.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="../includes/header.jsp"></jsp:include>

	<h3>글 목록</h3>

    <% 
        List<BoardVO> list = (List<BoardVO>) request.getAttribute("boardList");
    	SimpleDateFormat dt = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
    %>
    
    <table class="table mx-auto">
    	<thead>
    		
            <tr>
                <th>글 번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일시</th>
            </tr>
        </thead>
        
        <tbody>
    <%
    	for(BoardVO bvo : list) {
    		String wdate = dt.format(bvo.getWriteDate());
    %>
    	<!-- for문 안에 있어서 이 부분이 반복됨 -->
            <tr>
                <td class="mx-auto"><%= bvo.getBoardNo() %></td>
                <td><a href='board.do?bno=<%= bvo.getBoardNo() %>'><%= bvo.getTitle() %></a></td>
                <td><%= bvo.getMemberId() %></td>
                <td><%= wdate %></td>
            </tr>
    <%
    	}
    %>
        </tbody>
    </table>
    <!-- paging -->
    <%
    	PageDTO paging = (PageDTO) request.getAttribute("page");
    %>
    <%= paging %>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item disabled">
            	<a class="page-link">Previous</a>
            </li>
		    <% for (int p = paging.getStartPage(); p <= paging.getEndPage(); p++) { %>
	    	<li class="page-item">
	    		<a class="page-link" href="boardList.do?page=<%= p %>"><%= p %></a>
	   		</li>
		    <% } %>
            <li class="page-item">
            	<a class="page-link" href="#">Next</a>
            </li>
        </ul>
    </nav>
<jsp:include page="../includes/footer.jsp"></jsp:include>