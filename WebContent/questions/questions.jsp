<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div>
	<table>
		<tr>
			<th colspan="2" style="font-size: 30px">관리자 페이지</th>
		</tr>
		<tr>
			<th>
				<a href="${cp }/questions/subscontroller.do?cafe_admin=${cafe_admin }&cafe_num=${cafe_num }">가입질문생성</a>
			</th>
			<th>
				<a href="${cp }/semi/pagecontroller.do">회원대기목록</a>
			</th>
		</tr>		
		<tr>
			<th colspan="2" style="font-size: 20px">
				<input type="button" value="뒤로가기" onclick="idsearchCancel()">
			</th>
		</tr>	
	</table>
</div>
<script type="text/javascript">
	function idsearchCancel(){
		window.history.back();
	}
</script>