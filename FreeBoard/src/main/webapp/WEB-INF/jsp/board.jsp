<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.jin.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
    .reply ul {
        list-style-type: none;
    }
    .reply span {
        display: inline-block;
        margin: 5px 0;
        text-align: center;
    }
    
    .reply span:nth-of-type(2) {
        text-align: left;
    }
</style>

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
                    <c:if test="${ boardvo.img  != null }">
                        <td colspan="3">
                            <img src="images/${ boardvo.img }" alt="" width="300px" height="auto">
                        </td>
                    </c:if>
                </tr>
            </tbody>
        </table>
        <div class="mx-auto text-center">
            <button type="button" class="btn btn-warning text-center btn-lg m-1">수정</button>
            <button type="button" class="btn btn-danger text-center btn-lg m-1">삭제</button>
        </div>
    </form>

    <!-- 댓글관련 -->
    <div class="container reply">
        <div class="header">
            <textarea id="reply" class="col-sm-10" cols="30" rows="5"></textarea>
            <button id="replyAddBtn" class="">등록</button>
        </div>
        <div class="content">
            <ul>
                <li>
                    <span class="col-sm-2">글번호</span>
                    <span class="col-sm-5">내용</span>
                    <span class="col-sm-2">작성자</span>
                    <span class="col-sm-2">삭제</span>
                </li>
                <li>
                    <!-- 댓글 내용 -->
                </li>
            </ul>
        </div>
        <!-- 댓글 페이징 -->
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
    
    
<jsp:include page="../includes/footer.jsp"></jsp:include>
<script>
    let buttonEle = document.querySelectorAll("button");
    const bno = "${boardvo.boardNo}";
    const logId = "${logId}"; console.log(logId);
    console.log(bno);

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
<!-- <script src="js/replyService.js"></script>
<script src="js/reply.js"></script> -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="js/jreply.js"></script>