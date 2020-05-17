<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String users_id=(String)session.getAttribute("users_id");
	String cafe_admin=(String)session.getAttribute("cafe_admin");
	String userscafe=(String)session.getAttribute("userscafe");
	if(users_id==null||users_id.equals("")){
%>
		<a href="${cp }/semi/pagecontroller.do?check=1"><input type="button" value="로그인"></a>
		<a href="${cp }/semi/pagecontroller.do?check=2"><input type="button" value="회원가입"></a>
		<a href="${cp }/semi/pagecontroller.do?check=3"><input type="button" value="ID/PW찾기"></a>
<%
	}else if(cafe_admin!=null&&cafe_admin.equals("true")){
%>
		<h1>관리자</h1>
		<a href="${cp }/semi/pagecontroller.do?check=4"><input type="button" value="로그아웃"></a>
		<a href="${cp }/semi/pagecontroller.do?check=8"><input type="button" value="카페관리"></a>
		
<%
	}else if(userscafe!=null&&userscafe.equals("true")){
%>
		<a href="${cp }/semi/pagecontroller.do?check=4"><input type="button" value="로그아웃"></a>
		<a href="${cp }/semi/pagecontroller.do?check=6"><input type="button" value="카페탈퇴"></a>
<%
	}else{
%>
		<a href="${cp }/semi/pagecontroller.do?check=4"><input type="button" value="로그아웃"></a>
		<a href="${cp }/semi/pagecontroller.do?check=5"><input type="button" value="카페가입"></a>	
<%	
	}
%>