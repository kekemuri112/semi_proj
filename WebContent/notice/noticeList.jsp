<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
	<div id="notice_list">	
	</div>
<script>
<%
	int cafe_num=(int)session.getAttribute("cafe_num");
%>
	var cp='<c:out value="${cp}"/>';
	var scafe_admin='<c:out value="${cafe_admin}"/>';
	var cafe_admin=eval(scafe_admin);
	window.addEventListener('load', function() {
		getList1();
	})
	function getList1(){
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.status==200&&xhr.readyState==4){
				var data=xhr.responseText;
				var json=JSON.parse(data);
				var div=document.getElementById("notice_list");
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
					if(notice_lev==0){ // 큰게시판일때 + 모양으로 a태그생성.
						var aTag2=document.createElement("a");
						aTag2.href="javascript:insertNotice("+notice_ref+")"; //prompt 요청 / 게시판이름입력 호출
						var span=document.createElement("span");
						span.innerHTML="[+]";
						aTag2.appendChild(span);
						div.appendChild(aTag2);
						////////////////////////////
						
					}
					var aTag4=document.createElement("a");
					aTag4.href="javascript:deleteNotice("+notice_num+","+notice_lev+","+notice_ref+")"; //prompt 요청 / 게시판이름입력 호출
					var span=document.createElement("span");
					span.innerHTML="[-]";
					aTag4.appendChild(span);
					div.appendChild(aTag4);
					
					div.innerHTML+="<br>"
				}
				var aTag3=document.createElement("a");
				var span=document.createElement("span");
				aTag3.href="javascript:insertNotice()";
				span.innerHTML="[+]";
				aTag3.appendChild(span);
				div.appendChild(aTag3);
				
			}
		}
		xhr.open('get',cp+"/notice/list.do?cafe_num=<%=cafe_num%>",'true');
		xhr.send();
	}
	function insertNotice(notice_ref1){
		var notice_name=prompt("게시판이름을 입력하세요.");
		var notice_ref=0;
		if(notice_ref1!=null){
			notice_ref=notice_ref1;
		}
		console.log(notice_ref);
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.status==200&&xhr.readyState==4){
				var data=xhr.responseText;
				var json=JSON.parse(data);
				if(json.result){
					delete_div();
					getList1();
				}else{
					alert("게시판생성 실패!!")
				}
			}
		}
		xhr.open('post',cp+'/notice/insert.do','true');
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.send('notice_ref='+notice_ref+'&notice_name='+notice_name);
	}
	function delete_div(){ //div 목록초기화.
		var div=document.getElementById("notice_list")
		var com=div.childNodes
		for(var i=com.length-1;i>=0;i--){
			var comm=com.item(i);
			div.removeChild(comm);
		}
	}
	function deleteNotice(notice_num,notice_lev,notice_ref){
		console.log("notice_num : "+notice_num);
		console.log("notice_lev : "+notice_lev);
		console.log("notice_ref : "+notice_ref);
		if(confirm("삭제하시겠습니까?")){
			var xhr=new XMLHttpRequest();
			xhr.onreadystatechange=function(){
				if(xhr.status==200&&xhr.readyState==4){
					var data=xhr.responseText;
					var json=JSON.parse(data);
					if(json.result){
						alert("삭제성공!!")
						delete_div();
						getList1();
					}else{
						alert("삭제실패!!")
					}
				}
			}
			xhr.open('post',cp+'/notice/delete.do',true);
			xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			xhr.send('notice_lev='+notice_lev+'&notice_num='+notice_num+'&notice_ref='+notice_ref);
		}else{
			alert("false!!")
		}
	}
</script>