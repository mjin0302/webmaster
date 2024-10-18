<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

    <h3>등록화면 조회</h3>

    <% 
        String msg = (String) request.getAttribute("msg");
        String logId = (String) session.getAttribute("logId");
    %>
    <% 
	    if(msg != null) {
    %>
    	<p><%= msg %></p>
    <% 
	    }
    %>
    <form action="boardAdd.do" method="post">
        <table class="table">
            <tr>
                <th class="text-center">제목</th>
                <td>
                    <input type="text" class="form-control" name="title">
                </td>
            </tr>
            <tr>
                <th class="text-center">내용</th>
                <td>
                    <textarea class="form-control" name="content" id="" cols="30" rows="10"></textarea>
                    
                </td>
            </tr>
            <tr>
                <th class="text-center">작성자</th>
                <td>
                    <input type="text" class="form-control" value="<%= logId%>" readonly name="memberId">
                </td>
            </tr>
        </table>
        <div class="mx-auto text-center">
        	<button type="submit" class="btn btn-warning text-center btn-lg m-1">저장</button>
        </div>
    </form>
<jsp:include page="../includes/footer.jsp"></jsp:include>