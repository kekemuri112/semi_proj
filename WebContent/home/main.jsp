<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>/home/main.jsp</title>
<style>
 #wrap{width:1800px;height:980px;background-color: red;margin: auto;}
 #header1{width:25%;height:20%;background-color: blue;margin: auto;float: left;display: inline-block;}
 #header2{width:50%;height:20%;background-color: orange;margin: auto;float: left;display: inline-block;}
 #header3{width:25%;height:20%;background-color: grey;margin: auto;float: left;display: inline-block;}
 #header3-1{width: 80%;height: 80%;background-color: pink;margin-top: 10px;margin-left:50px;display: inline-block;}
 #menu{width:20%;height:80%;background-color: green;margin: auto;float: left;display: inline-block;}
 #list{width:100%;height:70%;background-color: green;margin: auto;float: left;display: inline-block;}
 #chat{width:100%;height:30%;background-color: orange;margin: auto;float: left;display: inline-block;}
 #main{width:80%;height:80%;background-color: yellow;margin: auto;float: left;display: inline-block;}
 #cafe{font-size: 80px;}
</style>
</head>
<body>
<div id="wrap">
	<div id="header1">
		<jsp:include page="${header1 }"/>
	</div>
	<div id="header2">
		<jsp:include page="${header2 }"/>
	</div>
	<div id="header3">
		<h3>상태표시줄..</h3>
		<div id="headerLog">
			<jsp:include page="${headerLog }"/>
		</div>
	</div>
	
	<div id="menu">
		<div id="list">
			<jsp:include page="${mlist }"/>
		</div>
		<div id="chat">
			<h1>채팅방임</h1>
		</div>
	</div>
	<div id="main">
		<div>
			<jsp:include page="${mfile }"/>
		</div>
	</div>
</div>
</body>
</html>