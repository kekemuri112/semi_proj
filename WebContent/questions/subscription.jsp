<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/questions/subscription.jsp</title>
</head>
<body>
	<div style="border-radius: 150px / 170px ; background-color: rgba(355,355,355,0.8);width:450px;margin: auto;margin-top: 180px;">
		<h1>가입 질문 생성</h1>
		<form method="post" action="${cp }/questions/subscontroller.do">
			<input type="hidden" name="cafe_num" value="${cafe_num }">
			<textarea rows="5" cols="50" name="question"></textarea><br><br><br>
			<input style=" width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" type="submit" value="등록">
			<input style=" width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" type="button" value="등록취소" onclick="subsCancel()"><br>
		</form>
		<br><br>
	</div>
	<div>
		<span id="reuslut">
			${msg }
		</span>
	</div>
</body>
<script type="text/javascript">
	function subsCancel(){
		window.history.back();
	}
</script>
</html>