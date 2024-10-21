<%@page import="com.jin.common.PageDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.jin.vo.BoardVO"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<h3>글 목록(boardList.jsp)</h3>

    <% 
        List<BoardVO> list = (List<BoardVO>) request.getAttribute("boardList");
    	SimpleDateFormat dt = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
    	
    	PageDTO paging = (PageDTO) request.getAttribute("page");
    	String sc = (String) request.getAttribute("searchCondition");
    	String kw = (String) request.getAttribute("keyword");
    	kw = kw == null ? "" : kw;
    %>
    
    <!-- page -->
    <% %>
    
    <!-- 검색조건 -->
    <form action="boardList.do" class="d-flex justify-content-end align-items-center text-end" style="width: 100%;">
        <div>
            <select id="inputState" name="searchCondition" class="form-select">
                <option selected value="">선택하세요.</option>
                <option value="T" <%= (sc != null && sc.equals("T") ? "selected" : "") %>>제목</option>
                <option value="W" <%= (sc != null && sc.equals("W") ? "selected" : "") %>>작성자</option>
                <option value="TW" <%= (sc != null && sc.equals("TW") ? "selected" : "") %>>제목 & 작성자</option>
            </select>
        </div>
        <div class="">
            <input type="text" class="form-control" id="keyword" name="keyword" value='<%=kw %>'>
        </div>

        <div class="">
            <button type="submit" class="btn btn-primary">조회</button>
        </div>
    </form>
    <table class="table mx-auto">
    	<thead>
            <tr>
                <th>글 번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일시</th>
                <th>조회수</th>
            </tr>
        </thead>
        
        <tbody>
        <c:if test="${ fn:length(boardList) > 0 }"></c:if>
        <c:forEach var="board" items="${ boardList }">
	        <c:choose>
				<c:when test="${board != null && fn:length(boardList) > 0}">
			    	<tr>
			            <td class="mx-auto"><c:out value="${ board.boardNo }" /></td>
			            <td>
			            	<a href='board.do?searchCondition=${ searchCondition }&keyword=${ keyword }&page=${ page.page }&bno=${ board.boardNo }'>${ board.title } </a>
			            </td>
			            <td><c:out value="${ board.memberId }" /></td>
			            <td><fmt:formatDate value="${ board.writeDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			            <td><c:out value="${ board.viewCnt }" /></td>
			        </tr>
				</c:when>
				<c:otherwise>
					<tr>
		                <td colspan="5" class="text-center">NO DATA!!!</td>
		            </tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
        </tbody>
    </table>
    
    <!-- page -->
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
        
	        <!-- 이전 페이지 여부 -->
	        <c:choose>
				<c:when test="${page.prev == true}">
					<li class="page-item">
				        <a class="page-link" href="boardList.do?page=${page.startPage - 1}">Prev</a>
				    </li>
				</c:when>
				<c:when test="${!page.prev}">
					<li class="page-item disabled">
				        <a class="page-link">Prev</a>
				    </li>
				</c:when>
			</c:choose>
			
			<!-- 페이지 번호 반복 -->
			<c:forEach var="p" begin="${page.startPage}" end="${page.endPage}">
			    <c:choose>
			        <c:when test="${page.page == p}">
			            <li class="page-item">
			                <a class="page-link active" aria-current="page" href="boardList.do?page=${p}">${p}</a>
			            </li>
			        </c:when>
			        <c:otherwise>
			            <li class="page-item">
			                <a class="page-link" href="boardList.do?searchCondition=${searchCondition}&keyword=${keyword}&page=${p}">${p}</a>
			            </li>
			        </c:otherwise>
			    </c:choose>
			</c:forEach>
			
			<c:choose>
				<c:when test="${page.next == true}">
					<li class="page-item">
				        <a class="page-link" href="boardList.do?page=${page.endPage + 1}">Next</a>
				    </li>
				</c:when>
				<c:when test="${!page.next}">
					<li class="page-item disabled">
				        <a class="page-link">Next</a>
				    </li>
				</c:when>
			</c:choose>
        </ul>
    </nav>