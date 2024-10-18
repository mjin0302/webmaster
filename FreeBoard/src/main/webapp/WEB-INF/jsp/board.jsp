<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.jin.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../includes/header.jsp"></jsp:include>
    <h3>상세페이지(board.jsp)</h3>
    
    <%
        BoardVO vo = (BoardVO) request.getAttribute("boardvo");
        String pg = (String) request.getAttribute("page");

        String sc = (String) request.getAttribute("searchCondition");
    	String kw = (String) request.getAttribute("keyword");
    	
    	kw = kw == null ? "" : kw;
    	
    	SimpleDateFormat dtFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
    	String wdate = dtFormat.format(vo.getWriteDate());
    %>
    <form action="boardUpdate.do" method="GET">
        <table class="table">
            <thead>
                <tr class="text-center">    
                    <th>글번호</th>
                    <td>13</td>
                    <th>조회수</th>
                    <td>3</td>
                </tr>
            </thead>
            <tbody>
                <tr class="text-center">
                    <th colspan="2" class="text-center">제묙</th>
                    <td colspan="2" class="form-control text-start"><%= vo.getTitle() %></td>
                </tr>
                <tr >
                    <th colspan="2" class="text-center">내용</th>
                    <td colspan="2" class="text-start">
                        <textarea name="" id="" cols="100%" rows="10" class="p-2 form-control" readonly><%= vo.getContent() %></textarea>
                    </td>
                </tr>
                <tr class="text-center">
                    <th colspan="2" class="text-center">작성자</th>
                    <td colspan="2" class="text-start form-control"><%= vo.getMemberId() %></td>
                </tr>
                <tr class="text-center">
                    <th colspan="2" class="text-center">작성일시</th>
                    <td colspan="2" class="text-start form-control"><%= wdate %></td>
                </tr>
            </tbody>
        </table>
        <div class="mx-auto text-center">
        	<button type="button" class="btn btn-warning text-center btn-lg m-1">수정</button>
            <button type="button" class="btn btn-danger text-center btn-lg m-1">삭제</button>
        </div>
        
    </form>
    
<jsp:include page="../includes/footer.jsp"></jsp:include>
<script>
    let buttonEle = document.querySelectorAll("button");

    buttonEle.forEach((ele) => {
        ele.addEventListener('click', (event) => {
            if(event.target.textContent == "수정") {
                location.href = 'boardUpdate.do?searchCondition=<%=sc %>&keyword=<%=kw %>&page=<%= pg %>&bno=<%= vo.getBoardNo() %>';
            } 
            if(event.target.textContent == "삭제") {
                location.href = 'boardDelete.do?searchCondition=<%=sc %>&keyword=<%=kw %>&page=<%= pg %>&bno=<%= vo.getBoardNo() %>';
            } 
        });
    });

</script>