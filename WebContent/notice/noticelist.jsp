<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
	<div id="contents_list">
		
	</div>
<script>
<%
	int cafe_num=(int)session.getAttribute("cafe_num");
%>
	var cp='<c:out value="${cp}"/>';
	var scafe_admin='<c:out value="${cafe_admin}"/>';
	var cafe_admin=eval(scafe_admin);
	window.addEventListener('load',function(){
		getList1();
	});
	
	function getList1(){
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.status==200&&xhr.readyState==4){
				console.log("dddd")
				var data=xhr.responseText;
				var json=JSON.parse(data);
				var div=document.getElementById("contents_list");
				for(var i=0;i<json.length;i++){
					var notice_name=json[i].notice_name;
					var notice_lev=json[i].notice_lev;
					var notice_ref=json[i].notice_ref;
					var notice_step=json[i].notice_step;
					var cafe_num=json[i].cafe_num;
					var notice_num=json[i].notice_num;
					if(notice_lev>0){
						div.innerHTML+="&ensp;&ensp;&ensp;ㄴ"
					}
					var aTag=document.createElement("a")
					aTag.href=cp+"/contents/contents.do?cafe_num="+cafe_num+"&notice_num="+notice_num;
					var span=document.createElement("span");
					span.innerHTML=notice_name;
					aTag.appendChild(span);
					div.appendChild(aTag);
					if(notice_lev==0&&cafe_admin){ // 큰게시판일때 + 모양으로 a태그생성.
						var aTag2=document.createElement("a");
						aTag2.href="javascript:insertNotice()"; //prompt 요청 / 게시판이름입력 호출
						var span=document.createElement("span");
						span.innerHTML="[+]";
						aTag2.appendChild(span);
						div.appendChild(aTag2);
					}
					div.innerHTML+="<br>"
				}
				if(cafe_admin){
					var aTag3=document.createElement("a");
					var span=document.createElement("span");
					aTag3.href="javascript:insertNotice()";
					span.innerHTML="[+]";
					aTag3.appendChild(span);
					div.appendChild(aTag3);
				}
			}
		}
		xhr.open('get','${cp}/notice/list.do?cafe_num=<%=cafe_num%>','true');
		xhr.send();
	}
</script>