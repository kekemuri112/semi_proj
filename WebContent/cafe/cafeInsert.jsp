<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<form style="border-radius: 100px / 100px ; background-color: rgba(355,355,355,0.8);width:550px;margin: auto;margin-top: 180px;" method="post" action="${cp }/cafe/cafeinsert.do" onsubmit="return checkA()" >
		<br>
		<p style="line-height:1px; font-size: 25px;">카페이름</p>
		<input type="text" name="cafe_name">
		<p style="line-height:1px; font-size: 25px;">카페소개글</p>
		<input type="text" name="cafe_desc">
		<p style="line-height:1px; font-size: 25px;">개설의도</p>
		<textarea rows="5" cols="50" name="cafe_intent"></textarea><br>
		<input style=" width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" type="submit" value="개설요청"><br>
		<br>
	</form>
	<br>
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
