<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id=(String)session.getAttribute("users_id");
	if(id==null||id.equals("")){
%>
		<a href="${cp }/semi/pagecontroller.do?check=1"><input type="button" value="로그인"></a>
		<a href="${cp }/semi/pagecontroller.do?check=2"><input type="button" value="회원가입"></a>
		<a href="${cp }/semi/pagecontroller.do?check=3"><input type="button" value="ID/PW찾기"></a>
<%
	}else{
%>
		<a href="${cp }/semi/pagecontroller.do?check=4"><input type="button" value="로그아웃"></a>
		<a href="${cp }/semi/pagecontroller.do?check=5"><input type="button" value="카페가입"></a>
		<a href="${cp }/semi/pagecontroller.do?check=6"><input type="button" value="카페탈퇴"></a>
<%
	}
%>