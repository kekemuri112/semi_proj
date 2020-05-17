<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% System.out.println(request.getParameter("idsearch")); %>
<div>
	<c:choose>
		<c:when test="${idsearch eq '1' }">
			<form method="post" action="${cp }/reg/idsearch.do">
				<table>
					<tr>
						<th colspan="2" style="font-size: 30px">아이디 찾기</th>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" name="users_name">
						</td>
					</tr>		
					<tr>
						<th>이메일</th>
						<td>
							<input type="text" name="users_email">
						</td>
					</tr>
					<tr>
						<th colspan="2" style="font-size: 30px">
							<input type="submit" value="찾기">
						</th>
					</tr>	
				</table>
			</form>
		</c:when>
		<c:otherwise>
			<form method="post" action="${cp }/reg/pwdsearch.do">
				<table>
					<tr>
						<th colspan="2" style="font-size: 30px">비밀번호 찾기</th>
					</tr>
					<tr>
						<th>아이디</th>
						<td>
							<input type="text" name="users_id">
						</td>
					</tr>		
					<tr>
						<th>이름</th>
						<td>
							<input type="text" name="users_name">
						</td>
					</tr>		
					<tr>
						<th>이메일</th>
						<td>
							<input type="text" name="users_email">
						</td>
					</tr>
					<tr>
						<th colspan="2" style="font-size: 30px">
							<input type="submit" value="찾기">
						</th>
					</tr>	
				</table>
			</form>
		</c:otherwise>
	</c:choose>
</div>
