<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


	<div  style="font-size:30px ; border-radius: 100px / 100px ; background-color: rgba(355,355,355,0.8);width:450px ;margin: auto;margin-top: 180px; " >
		<table>
			<tr>
				<th colspan="2" style="font-size: 70px; color:#F45D00 ;">계정 찾기</th>
			</tr>
			<tr></tr>
			<tr>
				<th>
					<a style="color: #F45D00; text-decoration:none; " href="${cp }/reg/idsearch.do">아이디 찾기</a>
					&ensp;&ensp;&ensp;
				</th>
				<th>
					<a style="color: #F45D00; text-decoration:none; " href="${cp }/reg/pwdsearch.do">비밀번호찾기</a>
					&ensp;&ensp;&ensp;
				</th>
			</tr>
			<tr></tr>		
			<tr>
				<th colspan="2" style="font-size: 20px">
					<input style="width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;" type="button" value="뒤로가기" onclick="idsearchCancel()">
				</th>
			</tr>	
		</table>
		<br>
	</div>

<script type="text/javascript">
	function idsearchCancel(){
		window.history.back();
	}
</script>
