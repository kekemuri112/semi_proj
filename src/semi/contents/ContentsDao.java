package semi.contents;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import semi.db.ConnectionPool;

public class ContentsDao {
	private static ContentsDao dao=new ContentsDao();
	private ContentsDao () {}
	
	public static ContentsDao getDao() {
		return dao;
	}
	public ArrayList<Integer> getNotice_num(int cafe_num) { //게시판번호구하기
		Connection con=null; 
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select notice_num from notice where cafe_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cafe_num);
			rs=pstmt.executeQuery();
			ArrayList<Integer> list=new ArrayList<Integer>();
			while(rs.next()) {
				int notice_num=rs.getInt("notice_num");
				list.add(notice_num);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public String getUsers_id(int users_num) { //유저번호받아서 유저아이디 출력
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=ConnectionPool.getCon();
			String sql="select users_id id from users where users_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, users_num);
			rs=pstmt.executeQuery();
			String id="";
			if(rs.next()) {
				id=rs.getString("id");
			}
			return id;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public ArrayList<Contents_ListVo> listAll (int cafe_num,int startRow,int endRow){
		Connection con=null;  //전체리스트뽑기
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Integer> list2=getNotice_num(cafe_num);
		try {
			con=ConnectionPool.getCon();
			
			String sql="select* from (select "+
			"aa.*, rownum rnum from (select * from contents where ";
			for(int i=0;i<list2.size();i++) {
				int num=list2.get(i);
				if(i!=list2.size()-1) {
					sql+="notice_num="+num+" or ";
				}else{
					sql+="notice_num="+num;
				}
			}
			sql+=" order by contents_num desc)aa  ) where rnum>=? and rnum<=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<Contents_ListVo> list=new ArrayList<Contents_ListVo>();
			while(rs.next()) {
				int index=rs.getInt("contents_num");
				String title=rs.getString("contents_title");
				int users_num=rs.getInt("users_num");
				String users_id=getUsers_id(users_num);
				Date contents_regDate=rs.getDate("contents_regdate");
				Date contents_modifyDate=rs.getDate("contents_modifydate");
				Contents_ListVo vo=new Contents_ListVo(index,title,users_id,contents_regDate,contents_modifyDate);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}	
	}
	public int getCount(int cafe_num) { //해당카페의 게시글 전체 행갯수 구함
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Integer> list2=getNotice_num(cafe_num);
		try {
			con=ConnectionPool.getCon();
			String sql="select nvl(count(*),0) cnt from contents where ";
			for(int i=0;i<list2.size();i++) {
				int num=list2.get(i);
				if(i!=list2.size()-1) {
					sql+="notice_num="+num+" or ";
				}else{
					sql+="notice_num="+num;
				}
			}
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int cnt=rs.getInt("cnt");
				return cnt;
			}
			return -1;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public Contents_detailVo detail(int contents_num) { // 게시글번호 파라미터로받아서  상세내용출력
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from contents where contents_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contents_num);
			rs=pstmt.executeQuery();
			Contents_detailVo vo=null;
			if(rs.next()) {
				String contents_title=rs.getString("contents_title");
				String users_id=getUsers_id(rs.getInt("users_num"));
				String post=rs.getString("contents_post");
				Date contents_regDate=rs.getDate("contents_regdate");
				Date contents_modifyDate=rs.getDate("contents_modifyDate");
				vo=new Contents_detailVo(contents_title, users_id, post, contents_regDate, contents_modifyDate);

			}
			return vo;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
