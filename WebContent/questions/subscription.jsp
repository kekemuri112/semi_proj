<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/questions/subscription.jsp</title>
</head>
<body>
	<div>
		<h1>가입 질문 생성</h1>
		<form method="post" action="/questions/subscontroller.do">
			<input type="hidden" name="cafe_num" value='111'><br>
			<textarea rows="5" cols="50" name="cafereg_question"></textarea><br>
			<input type="submit" value="가입목록">
			<input type="button" value="등록취소" onclick="subsCancel()">
		</form>
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