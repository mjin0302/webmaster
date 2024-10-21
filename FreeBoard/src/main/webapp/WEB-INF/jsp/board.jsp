<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.jin.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../includes/header.jsp"></jsp:include>
    <h3>상세페이지(board.jsp)</h3>
    <form action="boardUpdate.do" method="GET">
        <table class="table">
            <thead>
                <tr class="p-2 text-center">    
                    <th>글번호</th>
                    <td class="text-start">${boardvo.getBoardNo()}</td>
                    <th>작성자</th>
                    <td class="text-start">${boardvo.getMemberId()}</td>
                </tr>
            </thead>
            <tbody>
                <tr class="p-2 text-center">
                    <th>제목</th>
                    <td class="text-start">${boardvo.getTitle()}</td>
                    <th>조회수</th>
                    <td class="text-start">${boardvo.getViewCnt()}</td>
                </tr>
                <tr class="p-2 text-center">
                    <th class="text-center">작성일시</th>
                    <td colspan="3" class="text-start"><fmt:formatDate value="${ boardvo.writeDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr class="p-2 text-center">
                    <th class="text-center">내용</th>
                    <td colspan="3" class="text-center">
                        <textarea name="" id="" cols="100%" rows="7" class="form-control" readonly>${boardvo.getContent()}</textarea>
                    </td>
                </tr>
                <tr class="p-2">
                    <th class="text-center">이미지</th>
                    <c:if test="${ boardvo.getImg() } != null">
                        <td colspan="3"><img src="images/${ boardvo.getImg() }" alt="" width="300px" height="auto"></td>
                    </c:if>
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
                location.href = 'boardUpdate.do?searchCondition=${search}&keyword=${keyword}&page=${page}&bno=${boardvo.getBoardNo()}';
            } 
            if(event.target.textContent == "삭제") {
                location.href = 'boardDelete.do?searchCondition=${search}&keyword=${keyword}&page=${page}&bno=${boardvo.getBoardNo()}';
            } 
        });
    });

</script>