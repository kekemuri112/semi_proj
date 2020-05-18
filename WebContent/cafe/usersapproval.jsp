<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="approval">

</div>
<div id="page">

</div>
<script type="text/javascript">
	window.onload=function(){
		getList();
		getPage();
	}
	function getList(pageNum){
		var xhr=new XMLHttpRequest();
		var pageNum1=1;
		if(pageNum!=null){
			pageNum1=pageNum;
		}
		xhr.onreadystatechange = function(){
			if(xhr.readyState==4&&xhr.status==200){
				var data=xhr.responseText;
				var json=JSON.parse(data);
				var approval=document.getElementById("approval");
				var tableApp ="<table><tr><th>유저번호</th><th>유저아이디</th></tr>";
				for(var i=0;i<json.length-1;i++){
					var users_num=json[i].users_num;
					var users_id=json[i].users_id;
					var pageNum=json[json.length-1].pageNum;
					tableApp +="<tr><td>"+users_num+"</td><td>"+users_id+
								"<input type='button' value='거부' onclick='usersDel("+users_num+","+pageNum1+")'>"+
								"<input type='button' value='승인' onclick='usersAcc("+users_num+","+pageNum1+")'>"+
								"</td></tr>";
				}
				tableApp += "</table>";
				approval.innerHTML = tableApp;
			}
		}
		xhr.open('get','${cp}/users/usersapproval.do?pageNum='+pageNum1,true);
		xhr.send();
	}
	
	function getPage(pageNum){ //페이지처리
		var pageNum1=1;
		if(pageNum!=null){
			pageNum1=pageNum;
		}
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.status==200&&xhr.readyState==4){
				var data=xhr.responseText;
				var json=JSON.parse(data);
				var i=json.length-1;
				var startPage=json[i].startPage;
				var endPage=json[i].endPage;
				var pageCount=json[i].pageCount;
				var pageNum=json[i].pageNum;
				console.log(endPage);
				var div=document.getElementById("page");		
				if(startPage>10){
					div.innerHTML+="<a href='javascript:paging("+(startPage-1)+")'>[이전]</a>"
				}
				for(var j=startPage;j<=endPage;j++){
					div.innerHTML+="<a href='javascript:getList("+j+")'>["+j+"]</a>"
				}
				if(pageCount>endPage){
					div.innerHTML+="<a href='javascript:paging("+(endPage+1)+")'>[다음]</a>";			
				}
			}
		}
		xhr.open('get','${cp}/users/usersapproval.do?pageNum='+pageNum1,true);
		xhr.send();
	}
	function paging(pageNum) {
		getList(pageNum);
		document.getElementById("page").innerHTML="";
		getPage(pageNum);
	}
	//삭제
	function usersDel(users_num,pageNum) {
		console.log(cafe_num);
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.status==200&xhr.readyState==4){
				var data=xhr.responseText;
				var json=JSON.parse(data);
				if(json.result){
					paging(pageNum);
				}else{
					alert("삭제실패!!")
					paging(pageNum);
				}
			}
		}
		xhr.open('get','${cp}/users/usersdetail.do?cafe_num='+cafe_num,true);
		xhr.send();
	}
	//수락
	function usersAcc(users_num,pageNum) {
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.status==200&xhr.readyState==4){
				var data=xhr.responseText;
				var json=JSON.parse(data);
				if(json.result){
					paging(pageNum);
				}else{
					alert("삭제실패!!")
					paging(pageNum);
				}
			}
		}
		//console.log("cafe_num:"+cafe_num+",pageNum:"+pageNum);
		xhr.open('post','${cp}/users/usersstatus.do',true);
		xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
		xhr.send('users_num='+users_num);
	}
</script>

