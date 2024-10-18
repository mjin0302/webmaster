<%@page import="com.jin.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

    <h3>수정화면 조회(update.jsp)</h3>

    <% 
    	BoardVO vo = (BoardVO) request.getAttribute("boardvo");
        String msg = (String) request.getAttribute("msg");
        
        String pg = (String) request.getAttribute("page");
        
        String sc = (String) request.getAttribute("searchCondition");
    	String kw = (String) request.getAttribute("keyword");
    	kw = kw == null ? "" : kw;

        String logId = (String) session.getAttribute("logId");
    %>
    <% 
	    if(msg != null) {
    %>
    	<p><%= msg %></p>
    <% 
	    }
    %>
    <form action="boardUpdate.do" method="POST">
        <input type="hidden" class="" name="page" readonly value="<%= pg %>">
        <input type="hidden" class="" name="searchCondition" readonly value="<%=sc %>">
        <input type="hidden" class="" name="keyword" readonly value="<%=kw %>">
        <table class="table">
            <tr>
                <th class="text-center">글번호</th>
                <td>
                    <input type="text" class="form-control" name="bno" readonly value="<%= vo.getBoardNo() %>">
                </td>
            </tr>
            <tr>
                <th class="text-center">제목</th>
                <td>
                    <input type="text" class="form-control" name="title" value="<%= vo.getTitle() %>">
                </td>
            </tr>
            <tr>
                <th class="text-center">내용</th>
                <td>
                    <textarea class="form-control" name="content" id="" cols="30" rows="10"><%= vo.getContent() %></textarea>
                </td>
            </tr>
            <tr>
                <th class="text-center">작성자</th>
                <td>
                    <input type="text" class="form-control" name="memberId" readonly value="<%= vo.getMemberId() %>">
                </td>
            </tr>
        </table>
        
        <div class="mx-auto text-center">
        	<button type="submit" <%=logId != null && logId.equals(vo.getMemberId()) ? "" : "disabled" %> class="btn btn-warning text-center btn-lg m-1">저장</button>
        	<button type="reset" class="btn btn-secondary text-center btn-lg m-1">취소</button>
        </div>
        
    </form>
    
<jsp:include page="../includes/footer.jsp"></jsp:include>