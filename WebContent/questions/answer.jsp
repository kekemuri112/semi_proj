<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h1>카페에 질문들입니다.</h1>
	<form method="post" action="${cp }/questions/answer.do">
		<c:forEach var="q" items="${qlist }">
			<c:set var="i" value="${i+1 }"/>
			<h3>${i }번째 질문</h3>
			<h3>${q }</h3>
			<textarea rows="5" cols="50" name="answer">
			
			</textarea><br>
		</c:forEach>
		<input type="submit" value="가입요청">
		<input type="button" value="뒤로가기" onclick="qCancel()">
	</form>
</div>
<script type="text/javascript">
	function qCancel(){
		window.history.back();
	}
</script>