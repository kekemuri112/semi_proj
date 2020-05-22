<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String cafe_name=(String)session.getAttribute("cafe_name");
	String cafe_image=(String)session.getAttribute("cafe_image");
	if(cafe_name==null||cafe_name.equals("")){
%>
		<img src="${cp}/image/중고나라로고(아이콘만).png" style="width: 300px; margin-bottom: 40px;">
<%
	}else if(cafe_image==null||cafe_image.equals("")){
%>	
		<img src="${cp}/image/중고나라로고(아이콘만).png" style="width: 150px; margin-top: 10px;">
		<h1 style="color:#F45D00;font-size: 35px; "><%=cafe_name %></h1>
<%	
	}else {
%>	
		<img src="C:\server\apache-tomcat-8.5.53\apache-tomcat-8.5.53\wtpwebapps\semi_proj\upload/6.png" style="width: 300px; margin-bottom: 40px;">
<%
	}
%>
