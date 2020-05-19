package semi.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.ConnectionPool;

public class NoticeDao {
	private static NoticeDao instance =new NoticeDao();
	private NoticeDao() {}
	public static NoticeDao getInstance() {
		return instance;
	}	
	/*public int maxRef() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select max(notice_ref)+1 from notice";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
			
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
			if(pstmt!=null)pstmt.close();
			if(con!=null)con.close();
			}catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}*/
	public int delNotice(int notice_lev,int notice_ref,int notice_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="update notice set notice_name='������ �Խ����Դϴ�.' where notice_num=?";
			if(notice_lev==0) {
				sql+=" or notice_ref="+notice_ref;
			}
			System.out.println("delNotice sql�� : "+sql);
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			System.out.println("delNotice sql��  2: "+sql);
			return pstmt.executeUpdate();
			
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public int getStep(int notice_ref) { //step ���ϱ�
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select NVL(max(notice_step),0) step from notice";
			if(notice_ref>0) {
				sql+=" where notice_ref="+notice_ref;
			}
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("step");
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
	
	public int insert(int cafe_num,int notice_ref,String notice_name) { //�Խ��� �����
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="insert into notice(notice_num,cafe_num,notice_name,notice_ref,notice_lev,notice_step) "
					+ "values(notice_seq.nextval,'"+cafe_num+"','"+notice_name+"',";
			int notice_step=getStep(notice_ref)+1;
			if(notice_ref>0) { // �����Խ���
				sql+="'"+notice_ref+"',1,'"+notice_step+"')";
			}else if(notice_ref==0){ //ū�Խ���
				sql+="notice_seq.currval,0,'"+notice_step+"')";
			}
			pstmt=con.prepareStatement(sql);
			System.out.println("notice_insert �޼ҵ� SQL 2: "+sql);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
			if(pstmt!=null)pstmt.close();
			if(con!=null)con.close();
			}catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public String cafeAdmin(String users_id,int cafe_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from cafe where cafe_admin=? and cafe_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, users_id);
			pstmt.setInt(2, cafe_num);
			int n=pstmt.executeUpdate();
			if(n>0) {
				return "true";	
			}else {
				return "false";
			}
		}catch (SQLException se) {
			se.printStackTrace();
			return "false";
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}

	public String usersCafe(int users_num,int cafe_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from userscafe where users_num=? and cafe_num=? and userscafe_approved='����'";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, users_num);
			pstmt.setInt(2, cafe_num);
			int n=pstmt.executeUpdate();
			if(n>0) {
				return "true";	
			}else {
				return "false";
			}
		}catch (SQLException se) {
			se.printStackTrace();
			return "false";
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}
	
	public ArrayList<NoticeVo> listAll(int cafe_num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from notice where cafe_num=? order by notice_ref asc,notice_step asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cafe_num);
			rs=pstmt.executeQuery();
			ArrayList<NoticeVo> list=new ArrayList<NoticeVo>();
			while(rs.next()) {
				NoticeVo vo=new NoticeVo(
							rs.getInt("notice_num"),
							cafe_num,
							rs.getString("notice_name"),
							rs.getInt("notice_lev"),
							rs.getInt("notice_ref"),
							rs.getInt("notice_step"),
							rs.getInt("notice_grade")
						);
				
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
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}		
	}
	/*
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
	}*/
	/*public int delete(int notice_num) {
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		PreparedStatement pstmt4=null;
		try {
			con=ConnectionPool.getCon();
			String sql1="delete from notice where notice_num=?";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, notice_num);
			pstmt2=con.prepareStatement(sql1);
			pstmt2.setInt(1, notice_num);
			pstmt3=con.prepareStatement(sql1);
			pstmt3.setInt(1, notice_num);
			pstmt4=pstmt3;
			
			return pstmt4.executeUpdate();
		}catch (SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt1!=null)pstmt1.close();
				if(con!=null)con.close();
			}catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}*/
	
	public NoticeVo getVo(int notice_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from notice where notice_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			rs=pstmt.executeQuery();
			NoticeVo vo=null;
			if(rs.next()) {
				vo=new NoticeVo(
						notice_num,
						rs.getInt("cafe_num"),
						rs.getString("notice_name"),
						rs.getInt("notice_lev"),
						rs.getInt("notice_ref"),
						rs.getInt("notice_step"),
						rs.getInt("notice_grade")
					);
			}
			return vo;
		}catch (SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}
	
}
