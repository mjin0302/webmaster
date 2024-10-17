<%@page import="com.jin.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../includes/header.jsp"></jsp:include>

    <h3>회원목록</h3>
    <%
        List<MemberVO> list = (List<MemberVO>)request.getAttribute("memberList");
    %>
    <table class="table mx-auto">
        <tbody>
    <%
    	for(MemberVO mvo : list) {
    		
    %>
    	<!-- for문 안에 있어서 이 부분이 반복됨 -->
            <tr class>
                <td><%= mvo.getMemberId() %></td>
                <td><%= mvo.getMemberName() %></td>
                <td><%= mvo.getPhone() %></td>
            </tr>
    <%
    	}
    %>
        </tbody>
    </table>

<jsp:include page="../includes/footer.jsp"></jsp:include>