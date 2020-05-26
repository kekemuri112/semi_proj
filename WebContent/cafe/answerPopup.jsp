<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <div style="background-color: #F45D00;">
	<div style="margin: auto; background-color: #F45D00;">
		<h2 style="color: white;" id="answer"></h2>
	</div>
</div>
<script>
<%
	String susers_cafe_num=request.getParameter("users_cafe_num");
	int users_cafe_num=Integer.parseInt(susers_cafe_num);
%>
	window.onload=function(){
		answer();
	}
	
	function answer(){
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function (){
			if(xhr.status==200&&xhr.readyState==4){
				console.log(<%=users_cafe_num%>);
				var data=xhr.responseText;
				var json=JSON.parse(data);
				var div=document.getElementById("answer");
				for(var i=0;i<json.length;i++){
					var cafereg_question=json[i].cafereg_question;
					var answer_contents=json[i].answer_contents;
					div.innerHTML+="질문 "+(i+1)+" : "+cafereg_question+"<br>"+ "답변:"+answer_contents+"<br>";

					
				}
			}
		}
		xhr.open('post','${cp}/cafe/getAnswer.do',true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.send('users_cafe_num='+<%=users_cafe_num%>);
	}


</script>