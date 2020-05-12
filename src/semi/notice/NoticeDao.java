package semi.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.ConnectionPool;

public class NoticeDao {
	
	public int insert(NoticeVo vo) {
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		try {
			con=ConnectionPool.getCon();
			int cafe_num=vo.getCafe_num()+1;
			int notice_num=vo.getNotice_num();
			String notice_name=vo.getNotice_name();
			int notice_ref=vo.getNotice_ref();
			int notice_lev=vo.getNotice_lev();
			int notice_step=vo.getNotice_step();
			if(notice_num==0) {
				notice_ref=cafe_num;
			}else {
				String sql1="update notice set step=step+1 where ref=? and step>?";
				
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, notice_ref);
				pstmt1.setInt(2, notice_step);
				pstmt1.executeUpdate();
				notice_lev++;//(step=step+1)
				notice_step++;
			}
			String sql2="insert into notice values(notice_num.nextval,cafe_num.nextval,?,?,?,?)";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setString(1, notice_name);
			pstmt2.setInt(2, notice_ref);
			pstmt2.setInt(3, notice_lev);
			pstmt2.setInt(4, notice_step);
			pstmt2.executeUpdate();
			return 1;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
			if(pstmt1!=null)pstmt1.close();
			if(pstmt2!=null)pstmt2.close();
			if(con!=null)pstmt2.close();
			}catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}
	public ArrayList<String> listall(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select notice_name from notice ORDER BY cafe_name DESC";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<String> list=new ArrayList<String>();
			while(rs.next()) {
				list.add(rs.getString("notice_name"));
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
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}		
	}
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select NVL(count(num),0) cnt from notice";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0;
			}
			
		}catch (SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
			if(rs!=null)rs.close();
			if(pstmt!=null)rs.close();
			if(con!=null)rs.close();
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}
	}
	
}
