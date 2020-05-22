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
		<a href="${cp }/contents/contents.do"><img src="${cp}/image/중고나라로고(아이콘만).png" style="width: 150px; margin-top: 10px;"></a>
		<h1 style="color:#F45D00;font-size: 35px; "><%=cafe_name %></h1>
<%	
	}else {
%>	
		<a href="${cp }/contents/contents.do"><img src="${cp }/upload/<%=cafe_image %>" style="width: 150px; margin-bottom: 10px;"></a>
		<h1 style="color:#F45D00;font-size: 35px; "><%=cafe_name %></h1>
<%
	}
%>
