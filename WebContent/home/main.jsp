<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>/home/main.jsp</title>
<style>
	#wrap{width:1800px;height:1100px; background-color: rgba(255,255,255,0.2); margin: auto; position:relative; z-index: 2;}
	#header1{width:25%;height:20%; background-color: rgba(255,255,255,0.3); text-align:center ;border:90;border-color:grey; margin: auto;float: left;display: inline-block;}
	#header2{width:50%;height:20%; background-color: rgba(255,255,255,0.3); text-align:center ;margin: auto;float: left;display: inline-block;}
	#header3{width:25%;height:20%; background-color: rgba(255,255,255,0.3); text-align:center ;background-color:#F45D00;border-radius: 180px / 180px ;position: relative; margin: auto;float: left;display: inline-block;}
	#header3-1{width: 80%;height: 80%;text-align:center ; margin-top: 10px;margin-left:50px;display: inline-block;}
	#menu{width:20%;height:80%; border-radius: 80px / 70px;background-color:#F45D00;margin: auto;float: left;display: inline-block;}
	#list{width:100%;height:60%; margin: auto;float: left;display: inline-block; color: white;text-decoration: none;}
	#chat{width:100%;height:40%;text-align:center ;float: left ;text-align:center ; margin: auto;float: left;display: inline-block;}
	#main{width:80%;height:80%; background-color: rgba(255,255,255,0.2); text-align:center ;margin: auto;float: left;z-index: 2; display: inline-block;}
	#cafe{font-size: 80px;}
	table{ margin: auto; }
	a{text-decoration: none;color: black; font-size: 18px;}
</style>
</head>
<body>
<!--background-color: rgba(255,255,255,0.2); -->

<img src="${cp}/image/phone.gif" style="width: 1300px;position:absolute; right:180px; top:200px; ; z-index: 1; ">
<div id="wrap">
	<div id="header1">
		<div>
			<jsp:include page="${header1 }"/>
		</div>
	</div>
	<div id="header2">
		<div>
			<jsp:include page="${header2 }"/>
		</div>
	</div>
	<div id="header3">
		<h3 style="color: white;">
			<c:choose>
				<c:when test="${empty users_id }">
					<h1 style="color: white;">로그인이 필요합니다.</h1>				
				</c:when>
				<c:otherwise>
					<h1 style="color: white;">${users_id }</h1>
				</c:otherwise>
			</c:choose>
		</h3>
		<div id="headerLog">
			<div>
				<jsp:include page="${headerLog }"/>
			</div>
		</div>
	</div>
	
	<div id="menu">
		<div id="list">
			<div >
				<jsp:include page="${mlist }"/>
			</div>
		</div>
		<div id="chat">
			<div>
				<c:if test="${cafe_num>0 && not empty users_id }">
					<h1 style="color: white; font-size:170%">${cafe_name }채팅창..</h1>
					<jsp:include page="/chat/chatpage.jsp"/>
				</c:if>
			</div>
		</div>
	</div>
	<div id="main">
		<div>
			<div>
				<jsp:include page="${mfile }"/>
			</div>
		</div>
	</div>
</div>
</body>
</html>