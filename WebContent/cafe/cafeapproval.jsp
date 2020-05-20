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
				var tableApp ="<table style=' background-color: rgba(255,255,255,0.8); border:2px solid black;'><tr><th>카페명</th><th>소개글</th><th>개설의도</th><th>카페장</th><th>비고</th></tr>";
				for(var i=0;i<json.length-1;i++){
					var cafe_num=json[i].cafe_num;
					var cafe_name=json[i].cafe_name;
					var cafe_desc=json[i].cafe_desc;
					var cafe_intent=json[i].cafe_intent;
					var cafe_admin=json[i].cafe_admin;
					var pageNum=json[json.length-1].pageNum;
					tableApp +="<tr><td>"+cafe_name+"</td><td>"+cafe_desc+
								"</td><td>"+cafe_intent+"</td><td>"+cafe_admin+"</td><td>"+
								"<input style='width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;' type='button' value='거부' onclick='cafeDel("+cafe_num+","+pageNum1+")'>"+
								"<input style='width:75px;height:30px; border-radius: 25px/25px;  background-color:white; outline-style:hidden;' type='button' value='승인' onclick='cafeAcc("+cafe_num+","+pageNum1+")'>"+
								"</td></tr>";
				}
				tableApp += "</table>";
				approval.innerHTML = tableApp;
			}
		}
		xhr.open('get','${cp}/cafe/cafeapproval.do?pageNum='+pageNum1,true);
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
		xhr.open('get','${cp}/cafe/cafeapproval.do?pageNum='+pageNum1,true);
		xhr.send();
	}
	function paging(pageNum) {
		getList(pageNum);
		document.getElementById("page").innerHTML="";
		getPage(pageNum);
	}
	function cafeDel(cafe_num,pageNum) {
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
		xhr.open('get','${cp}/cafe/cafestatus.do?cafe_num='+cafe_num,true);
		xhr.send();
	}
	
	function cafeAcc(cafe_num,pageNum) {
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
		console.log("cafe_num:"+cafe_num+",pageNum:"+pageNum);
		xhr.open('post','${cp}/cafe/cafestatus.do',true);
		xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
		xhr.send('cafe_num='+cafe_num);
	}
</script>

