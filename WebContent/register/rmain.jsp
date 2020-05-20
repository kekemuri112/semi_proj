<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<style>
	.regC{ width: 75px;height: 40px;background-color:white; outline-style:hidden;    border: 10px;border-radius: 20px / 20px; }
</style>  
    
<c:choose>
	<c:when test="${empty users_id }">
		<br>
		<a href="${cp }/semi/pagecontroller.do?check=1&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="로그인" class="regC"></a>
		<a href="${cp }/semi/pagecontroller.do?check=2&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="회원가입" class="regC"></a>
		<a href="${cp }/semi/pagecontroller.do?check=3&cafe_num=${cafe_num }&notice_num=${notice_num }"><input type="button" value="ID/PW찾기" class="regC"></a>
	</c:when>
	<c:when test="${cafe_admin eq 'true' }">
		<h1 style="color:white; font-size: 1.5em;" >카페관리자${users_id }</h1>
		<br>
		<a href="${cp }/semi/pagecontroller.do?check=4&notice_num=${notice_num }"><input type="button" value="로그아웃" class="regC"></a>
		<a href="${cp }/semi/pagecontroller.do?check=8&cafe_admin=${cafe_admin }"><input type="button" value="카페관리" class="regC"></a>
		<a href="${cp }/semi/pagecontroller.do?check=9&notice_num=${notice_num }"><input type="button" value="정보수정" class="regC"></a>
	</c:when>
	<c:when test="${userscafe eq 'true' }">
		<h1  style="color:white; font-size: 1.5em;" >카페원${users_id }</h1>
		<br>
		<a href="${cp }/semi/pagecontroller.do?check=4&notice_num=${notice_num }"><input type="button" value="로그아웃" class="regC"></a>
		<a href="${cp }/semi/pagecontroller.do?check=6&notice_num=${notice_num }"><input type="button" value="카페탈퇴" class="regC"></a>
		<a href="${cp }/semi/pagecontroller.do?check=9&notice_num=${notice_num }"><input type="button" value="정보수정" class="regC"></a>
	</c:when>
	<c:when test="${userscafeApproved eq 'true' }">
		<h1  style="color:white; font-size: 1.5em;" >승인대기${users_id }</h1>
		<br>
		<a href="${cp }/semi/pagecontroller.do?check=4&notice_num=${notice_num }"><input type="button" value="로그아웃" class="regC"></a>
		<a href="${cp }/semi/pagecontroller.do?check=9&notice_num=${notice_num }"><input type="button" value="정보수정" class="regC"></a>
	</c:when>
	<c:when test="${cafe_num eq 0}">
		<a href="${cp }/semi/pagecontroller.do?check=4&notice_num=${notice_num }"><input type="button" value="로그아웃" class="regC"></a>
		<a href="${cp }/semi/pagecontroller.do?check=9&notice_num=${notice_num }"><input type="button" value="정보수정" class="regC"></a>	
	</c:when>	
	<c:otherwise>
		<h1  style="color:white; font-size: 1.5em;" >비회원</h1>
		<a href="${cp }/semi/pagecontroller.do?check=4&notice_num=${notice_num }"><input type="button" value="로그아웃" class="regC"></a>
		<a href="${cp }/semi/pagecontroller.do?check=5"><input type="button" value="카페가입" class="regC"></a>	
		<a href="${cp }/semi/pagecontroller.do?check=9&notice_num=${notice_num }"><input type="button" value="정보수정" class="regC"></a>	
	</c:otherwise>	
</c:choose>
