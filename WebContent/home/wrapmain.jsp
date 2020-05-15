<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String cafe_name=(String)session.getAttribute("cafe_name");
	String users_name=(String)session.getAttribute("users_name");
	if(cafe_name==null||cafe_name.equals("")){
%>
		<h1>네이버 카페 인척하는 3조 카페</h1>
<%
	}else {
%>
		<h1><%=cafe_name %></h1>
<%
	}
%>