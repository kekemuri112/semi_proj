<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<h1>${desc }</h1>
	<form style="border-radius: 100px / 100px ; background-color: rgba(355,355,355,0.8);width:450px;margin: auto;margin-top: 30px;"
	 method="post" action="${cp }/questions/answer.do">
		<c:choose>
			<c:when test="${qlist eq null }">
				<h2>질문은 없습니다.</h2>
			</c:when>
			<c:otherwise>
				<h2>카페에 질문들입니다.</h2>
				<c:forEach var="vo" items="${qlist }">
					<c:set var="i" value="${i+1 }"/>
					<h3>${i }번째 질문</h3>
					<h3>${vo.cafereg_question }</h3>
					<textarea rows="5" cols="50" name="answer"></textarea><br>
					<input type="hidden" value=${vo.cafereg_num } name="cafereg_num">
					<input type="hidden" value=${cafe_num } name="cafe_num">
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<input type="submit" value="가입요청" style=" width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;">
		<input type="button" value="뒤로가기" onclick="qCancel()" style=" width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;">
	</form>
</div>
<script type="text/javascript">
	function qCancel(){
		window.history.back();
	}
</script>