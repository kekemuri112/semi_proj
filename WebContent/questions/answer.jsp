<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<form style="border-radius: 100px / 100px ; background-color: rgba(355,355,355,0.8);width:450px;margin: auto;margin-top: 180px;"
	 method="post" action="${cp }/questions/answer.do">
		<c:choose>
			<c:when test="${qlist eq null }">
				<h1>질문은 없습니다.</h1>
			</c:when>
			<c:otherwise>
				<h1>카페에 질문들입니다.</h1>
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
		<input type="submit" value="가입요청">
		<input type="button" value="뒤로가기" onclick="qCancel()">
	</form>
</div>
<script type="text/javascript">
	function qCancel(){
		window.history.back();
	}
</script>