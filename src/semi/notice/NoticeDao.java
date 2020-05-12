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
	
	public int insert(NoticeVo vo) {
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		try {
			con=ConnectionPool.getCon();
			int cafe_num=vo.getCafe_num();
			String notice_name=vo.getNotice_name();
			int notice_ref=vo.getNotice_ref();
			int notice_lev=vo.getNotice_lev();
			int notice_step=vo.getNotice_step();
			int notice_grade=vo.getNotice_grade();
				String sql1="update notice set step=step+1 where ref=? and step>?";
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, notice_ref);
				pstmt1.setInt(2, notice_step);
				pstmt1.executeUpdate();
				notice_lev++;//(step=step+1)
				notice_step++;
			
			String sql2="insert into notice values(notice_seq.nextval,?,?,?,?,?,?)";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, cafe_num);
			pstmt2.setString(2, notice_name);
			pstmt2.setInt(3, notice_ref);
			pstmt2.setInt(4, notice_lev);
			pstmt2.setInt(5, notice_step);
			pstmt2.setInt(6, notice_grade);
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
	public ArrayList<NoticeVo> listAll(int cafe_num){
		System.out.println(cafe_num+"listAll메소드에 접근함.");
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from notice where cafe_num=? order by notice_ref desc,notice_step asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cafe_num);
			rs=pstmt.executeQuery();
			ArrayList<NoticeVo> list=new ArrayList<NoticeVo>();
			while(rs.next()) {
				System.out.println("rs.next() 실행중..");
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
			System.out.println("리턴전..");
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			System.out.println("리턴불가 NULL ");
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
	public int delete(int notice_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="delete from notice where notice_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			return pstmt.executeUpdate();
		}catch (SQLException se) {
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
	}
	
}
