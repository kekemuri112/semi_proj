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
	public String getUsers_id(int users_num) { //
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
			sql+=")aa  order by rnum desc) where rnum>=? and rnum<=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<Contents_ListVo> list=new ArrayList<Contents_ListVo>();
			while(rs.next()) {
				int index=rs.getInt("rnum");
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
}
