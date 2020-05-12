<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.SQLException"%>
<%@page import="semi.db.ConnectionPool"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String users_id=request.getParameter("users_id");
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String id="사용가능";
	try{
		con=ConnectionPool.getCon();
		String sql="";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, users_id);
		rs=pstmt.executeQuery();
		if(rs.next()){
			id="사용불가능.";
		}
	}catch(SQLException se){
		se.printStackTrace();
	}finally{
		try{
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}catch(SQLException s){
			s.printStackTrace();
		}
	}
	response.setContentType("text/xml;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	pw.print("<result>");
	pw.print("<idcheck>"+id+"</idcheck>");
	pw.print("</result>");
%>