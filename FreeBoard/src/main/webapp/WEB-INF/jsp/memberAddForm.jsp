<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="../includes/header.jsp"></jsp:include>

<form action="memberAdd.do">
	<table class="table">
		<tr>
			<th class="text-center">회원ID</th>
			<td class="text-start"><input type="text" name="mid" class="p-1 form-control"></td>
		</tr>
		<tr>
			<th class="text-center">회원이름</th>
			<td class="text-start"><input type="text" name="mname" class="p-1 form-control"></td>
		</tr>
		<tr>
			<th class="text-center">비밀번호</th>
			<td class="text-start"><input type="password" name="passwd" class="p-1 form-control"></td>
		</tr>
		<tr>
			<th class="text-center">연락처</th>
			<td class="text-start"><input type="text" name="phone" class="p-1 form-control"></td>
		</tr>
		<tr>
			<td colspan="2" class="text-center"><input type="submit" value="저장" class="btn btn-primary p-1"></td>
		</tr>
	</table>
</form>

<jsp:include page="../includes/footer.jsp"></jsp:include>