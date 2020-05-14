<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<body>
	<div>
		<table>
			<tr>
				<th colspan="2" style="font-size: 30px">계정 찾기</th>
			</tr>
			<tr>
				<th>
					<a href="#">아이디 찾기</a>
				</th>
				<th>
					<a href="#">비밀번호찾기</a>
				</th>
			</tr>		
			<tr>
				<th colspan="2" style="font-size: 20px">
					<input type="button" value="뒤로가기" onclick="idsearchCancel()">
				</th>
			</tr>	
		</table>
	</div>
</body>
<script type="text/javascript">
	function idsearchCancel(){
		window.history.back();
	}
</script>
