<%@page import="com.jin.service.MemberServiceImpl"%>
<%@page import="com.jin.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- MVC 디자인 : View(JSP페이지), Model(DB처리), 컨트롤 -->
	<% String myName = "킴옹심";
		MemberService service = new MemberServiceImpl();
		if(service.retireMember("guest1")) {
	%>
			<p>삭제되었습니다.</p>

	<%
		} else {
	%>
			<p>회원 정보가 없습니다. </p>
	<%
		}
	%>
	
	<h3>내 이름은<%=myName %> 입니다.</h3>
</body>
</html>