<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String msg=(String)request.getAttribute("msg");
%>
<h1><%=msg %></h1>
<c:if test="${msg eq '맞는 정보가 없습니다 다시 한번 확인 바랍니다.' }">
	<a href="javascript:history.back()">이전으로.</a>		
</c:if>