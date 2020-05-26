<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div>
	<table style="font-size:30px ; border-radius: 100px / 100px ; background-color: rgba(355,355,355,0.8);width:450px ;margin: auto;margin-top: 180px; ">
		<tr>
			<th colspan="2" style="font-size: 30px">관리자 페이지</th>
		</tr>
		<tr>
			<th>
				<a href="${cp }/questions/subscontroller.do?cafe_admin=${cafe_admin }&cafe_num=${cafe_num }">가입질문생성</a>
			</th>
			<th>
				<a href="${cp }/semi/pagecontroller.do?check=10">회원관리</a>
			</th>
		</tr>		
		<tr>
			<th colspan="2" style="font-size: 20px">
				<input  style=" width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" type="button" value="뒤로가기" onclick="idsearchCancel()">
			</th>
		</tr>
		<tr></tr>	
	</table>
</div>
<script type="text/javascript">
	function idsearchCancel(){
		window.history.back();
	}
</script>
