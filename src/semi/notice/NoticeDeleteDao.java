package semi.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.contents.ContentsDao;
import semi.db.ConnectionPool;

public class NoticeDeleteDao {
	private static NoticeDeleteDao instance=new NoticeDeleteDao();
	private NoticeDeleteDao () {}
	
	public static NoticeDeleteDao getInstance() {
		return instance;
	}
	public int delete_notice(int notice_num) {//지우는기능
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			ArrayList<Integer> list=getContents_num(notice_num);
			if(list.size()>0) {
				ContentsDao dao=ContentsDao.getDao();
				for(int i=0;i<list.size();i++) {
					dao.delete(list.get(i)); // contentsDao의 댓글지우는 메소드 사용함.
				}
			}
			ArrayList<Integer> list2=getNotice_num(notice_num);
			con=ConnectionPool.getCon();
			String sql="delete from notice where ";
			if(list2.size()==1) {
				sql+="notice_num="+list2.get(0);
			}else if(list2.size()>1){
				for(int i=0;i<list2.size();i++) {
					if(i==list2.size()-1) {
						sql+="notice_num="+list2.get(i);
					}else {
						sql+="notice_num="+list2.get(i)+" or ";
					}
				}
			}else {
				sql+="notice_num="+notice_num;
			}
			System.out.println("delete_notice 메소드의 sql : "+sql);
			pstmt=con.prepareStatement(sql);
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
	public ArrayList<Integer> getContents_num(int notice_num) { //게시판에 속하는 게시글넘버 뽑아오기.
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select contents_num from contents where ";
			if(getNotice_lev(notice_num)==0) {
				ArrayList<Integer>list=getNotice_num(notice_num);
				if(list.size()==1) {
					sql+="notice_num="+notice_num;
				}else if(list.size()>1) {
					for(int i=0;i<list.size();i++) {
						if(i==list.size()-1) {
							sql+="notice_num="+list.get(i);
						}else {
							sql+="notice_num="+list.get(i)+" or ";
						}
					}
				}
			}else if(getNotice_lev(notice_num)==1) {
				sql+="notice_num="+notice_num;
			}
			pstmt=con.prepareStatement(sql);
			System.out.println("getContents_num SQL: "+sql);
			rs=pstmt.executeQuery();
			ArrayList<Integer>list2=new ArrayList<Integer>();
			while(rs.next()) {
				list2.add(rs.getInt("contents_num"));
			}
			return list2;
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
	public int getNotice_lev(int notice_num) { //게시판 lev 구하기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select notice_lev from notice where notice_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("notice_lev");
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
	public ArrayList<Integer> getNotice_num(int notice_num) { //속한게시판 게시판번호 구하기.
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select notice_num from notice where notice_ref=? and notice_lev>=0";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			ArrayList<Integer>list =new ArrayList<Integer>();
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt("notice_num"));
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			
		}
	}
}
