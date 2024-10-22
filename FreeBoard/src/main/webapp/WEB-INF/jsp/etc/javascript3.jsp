<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h3>javascript.jsp</h3>

<table class="table text-center mx-auto" style="width: 50%;">
	<tr>
		<th>아이디</th>
		<td><input type="text" id="mid" name="mid"></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="text" id="mpass" name="mpass"></td>
	</tr>
	<tr>
		<th>이름</th>
		<td><input type="text" id="mname" name="mname"></td>
	</tr>
	<tr>
		<th>연락처</th>
		<td><input type="tel" id="mphone" name="mphone"></td>
	</tr>
	<tr>
		<td colspan="2" class="text-center">
			<button type="submit" id="addBtn">등록</button>
		</td>
	</tr>
</table>	

<div id="show" class="mt-5">
	<!-- 회원목록 출력 -->
	<table class="table">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>연락처</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>


		</tbody>
	</table>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script src="js/members.js"></script>