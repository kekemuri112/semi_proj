package semi.contents;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import semi.db.ConnectionPool;

public class ContentsDaoBackup {
	private static ContentsDaoBackup dao=new ContentsDaoBackup();
	private ContentsDaoBackup () {}
	
	public static ContentsDaoBackup getDao() {
		return dao;
	}
	public int getComments(int contents_num) {
		Connection con=null; 
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select count(*) from comments where contents_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contents_num);
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
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
	
	
	//게시판 이름 가져오기
	public String getNotice_name(int notice_num) {
		Connection con=null; 
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select notice_name from notice where notice_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			rs=pstmt.executeQuery();
			String notice_name=null;
			if(rs.next()) {
				notice_name=rs.getString("notice_name");
			}
			return notice_name;
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
	
	public ArrayList<Integer> getNotice_num(int cafe_num) {
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
	public String getUsers_id(int users_num) { //������ȣ�޾Ƽ� �������̵� ���
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
	public ArrayList<Contents_ListVo> listAll (int cafe_num,int startRow,int endRow,int notice_num){
		Connection con=null;  
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			
			String sql="select * from (select "+
			"aa.*, rownum rnum from (select * from contents where ";
			if(notice_num==0) { 
				ArrayList<Integer> list2=getNotice_num(cafe_num);
				for(int i=0;i<list2.size();i++) {		
					int num=list2.get(i);
					if(i!=list2.size()-1) {
						sql+=" notice_num="+num+" or ";
					}else{
						sql+=" notice_num="+num;
					}
				}
			}else {
				sql+=" notice_num="+notice_num;
			}	
			sql+=" order by contents_num desc)aa  ) where rnum>=? and rnum<=? ";
			System.out.println("list sql문..:"+sql);
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<Contents_ListVo> list=new ArrayList<Contents_ListVo>();
			while(rs.next()) {
				int contents_num=rs.getInt("contents_num");
				String title=rs.getString("contents_title");
				int users_num=rs.getInt("users_num");
				String users_id=getUsers_id(users_num);
				Date contents_regDate=rs.getDate("contents_regdate");
				Date contents_modifyDate=rs.getDate("contents_modifydate");
				Contents_ListVo vo=new Contents_ListVo(contents_num,title,users_id,contents_regDate,contents_modifyDate,users_num);
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
	public int getCount(int cafe_num,int notice_num) { //�ش�ī���� �Խñ� ��ü �హ�� ����
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select nvl(count(*),0) cnt from contents where ";
			if(notice_num>0) {
				sql+="notice_num="+notice_num;
			}else{
				ArrayList<Integer> list2=getNotice_num(cafe_num);
				for(int i=0;i<list2.size();i++) {
					int num=list2.get(i);
					if(i!=list2.size()-1) {
						sql+="notice_num="+num+" or ";
					}else{
						sql+="notice_num="+num;
					}
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
	public Contents_detailVo detail(int contents_num) { // �Խñ۹�ȣ �Ķ���ͷι޾Ƽ�  �󼼳������
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
				int users_num=rs.getInt("users_num");
				String users_id=getUsers_id(users_num);
				String post=rs.getString("contents_post");
				Date contents_regDate=rs.getDate("contents_regdate");
				Date contents_modifyDate=rs.getDate("contents_modifyDate");
				vo=new Contents_detailVo(contents_title, users_id, post, contents_regDate, contents_modifyDate,contents_num,users_num);
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
	public int insert(ContentsVo vo) { //�Խñ��ۼ� �޼ҵ�
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="insert into contents values(content_seq.nextval,?,?,?,?,sysdate,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getNotice_num());
			pstmt.setInt(2, vo.getUsers_num());
			pstmt.setString(3,vo.getContents_title());
			pstmt.setString(4,vo.getContents_post());
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
	public int getCafe_Num(int notice_num) { // �Խ��� ��ȣ�޾Ƽ� ���ī�信 ���ϴ��� ī���ȣ ���ϱ�.
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select cafe_num from notice where notice_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cafe_num");
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
	public int update_point(String contents_title,String contents_post,int contents_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="update contents set contents_title=?, contents_post=?,contents_modifydate=sysdate where contents_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, contents_title);
			pstmt.setString(2, contents_post);
			pstmt.setInt(3, contents_num);
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
	public int updatePoint(int users_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="update user_cafe set users_cafe_point=users_cafe_point+100 where users_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, users_num);
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
}
