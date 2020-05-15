<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<form method="post" action="${cp }/cafe/cafeinsert.do" onsubmit="return checkA()" >
		카페이름<br>
		<input type="text" name="cafe_name"><br>
		카페소개글<br>
		<input type="text" name="cafe_desc"><br>
		개설의도<br>
		<input type="text" name="cafe_intent"><br>
		<input type="submit" value="개설요청">
	</form>
</div>
<script type="text/javascript">
	function checkA() {
		var cafe_name=document.getElementsByName("cafe_name")[0];
		var cafe_desc=document.getElementsByName("cafe_desc")[0];
		var cafe_intent=document.getElementsByName("cafe_intent")[0];
		if(cafe_name.value==null || cafe_name.value==""){
			return false;
		}else if(cafe_desc.value==null  || cafe_desc.value==""){
			return false;
		}else if(cafe_intent.value==null  || cafe_intent.value==""){
			return false;
		}else{
			return true;
		}
	}
</script>
