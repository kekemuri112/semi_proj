<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
    
<c:choose>
	<c:when test="${empty users_id }">
		<a href="${cp }/semi/pagecontroller.do?check=1&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="로그인"></a>
		<a href="${cp }/semi/pagecontroller.do?check=2&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="회원가입"></a>
		<a href="${cp }/semi/pagecontroller.do?check=3&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="ID/PW찾기"></a>
	</c:when>
	<c:when test="${cafe_admin eq 'true' }">
		<h1>카페관리자${users_id }</h1>
		<a href="${cp }/semi/pagecontroller.do?check=4&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="로그아웃"></a>
		<a href="${cp }/semi/pagecontroller.do?check=8&cafe_num=<%=request.getParameter("cafe_num") %>&cafe_admin=${cafe_admin }"><input type="button" value="카페관리"></a>
		<a href="${cp }/semi/pagecontroller.do?check=9&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="정보수정"></a>
	</c:when>
	<c:when test="${userscafe eq 'true' }">
		<h1>카페원${users_id }</h1>
		<a href="${cp }/semi/pagecontroller.do?check=4&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="로그아웃"></a>
		<a href="${cp }/semi/pagecontroller.do?check=6&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="카페탈퇴"></a>
		<a href="${cp }/semi/pagecontroller.do?check=9&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="정보수정"></a>
	</c:when>
	<c:otherwise>
		<h1>그냥눈팅....</h1>
		<a href="${cp }/semi/pagecontroller.do?check=4&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="로그아웃"></a>
		<a href="${cp }/semi/pagecontroller.do?check=5&cafe_num=<%=request.getParameter("cafe_num") %>"><input type="button" value="카페가입"></a>	
		<a href="${cp }/semi/pagecontroller.do?check=9&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="정보수정"></a>	
	</c:otherwise>	
</c:choose>

<%--
	String users_id=(String)session.getAttribute("users_id");
	String cafe_admin=(String)request.getAttribute("cafe_admin");
	String userscafe=(String)request.getAttribute("userscafe");
	
	if(users_id==null||users_id.equals("")){
%>
		<a href="${cp }/semi/pagecontroller.do?check=1&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="로그인"></a>
		<a href="${cp }/semi/pagecontroller.do?check=2&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="회원가입"></a>
		<a href="${cp }/semi/pagecontroller.do?check=3&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="ID/PW찾기"></a>
<%
	}else if(cafe_admin!=null&&cafe_admin.equals("true")){
%>
		<h1>카페관리자${users_id }</h1>
		<a href="${cp }/semi/pagecontroller.do?check=4&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="로그아웃"></a>
		<a href="${cp }/semi/pagecontroller.do?check=8&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="카페관리"></a>
		<a href="${cp }/semi/pagecontroller.do?check=9&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="정보수정"></a>
<%
	}else if(userscafe!=null&&userscafe.equals("true")){
%>
		<h1>카페원${users_id }</h1>
		<a href="${cp }/semi/pagecontroller.do?check=4&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="로그아웃"></a>
		<a href="${cp }/semi/pagecontroller.do?check=6&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="카페탈퇴"></a>
		<a href="${cp }/semi/pagecontroller.do?check=9&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="정보수정"></a>
<%
	}else{
%>
		${users_id }
		<h1>그냥눈팅....</h1>
		<a href="${cp }/semi/pagecontroller.do?check=4&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="로그아웃"></a>
		<a href="${cp }/semi/pagecontroller.do?check=5&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="카페가입"></a>	
		<a href="${cp }/semi/pagecontroller.do?check=9&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="정보수정"></a>	
<%	
	}
--%>