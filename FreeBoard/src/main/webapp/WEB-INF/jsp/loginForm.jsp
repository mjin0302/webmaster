<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>로그인화면(loginForm.jsp)</h3>

<form action="loginForm.do" method="POST">
    <table class="table">
        <tr>
            <th class="text-center">아이디</th>
            <td class="text-center">
                <input type="text" class="uId" name="uId">
            </td>
        </tr>
        <tr>
            <th class="text-center">비밀번호</th>
            <td class="text-center">
                <input type="password" class="uPass" name="uPass">
            </td>
        </tr>
        <tr>
            <td colspan="2" class="text-center">
                <button type="submit" class="btn btn-success">로그인</button>
            </td>
        </tr>
    </table>
</form>


<jsp:include page="../includes/footer.jsp"></jsp:include>