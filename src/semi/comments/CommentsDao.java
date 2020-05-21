package semi.comments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.contents.Contents_ListVo;
import semi.db.ConnectionPool;

public class CommentsDao {
	private static CommentsDao instance=new CommentsDao();
	private CommentsDao() {}
	public static CommentsDao getInstance() {
		return instance;
	}
	
	public Comments_infoVo getInfo(int comments_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from comments where comments_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, comments_num);
			rs=pstmt.executeQuery();
			Comments_infoVo vo=new Comments_infoVo();
			if(rs.next()) {
				vo.setComments_num(comments_num);
				vo.setContents_num(rs.getInt("contents_num"));
				vo.setComments_lev(rs.getInt("comments_lev"));
				vo.setComments_ref(rs.getInt("comments_ref"));
				vo.setComments_step(rs.getInt("comments_step"));
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
	
	public int getCount(int contents_num) { // �ش�Խñ��� ��� ��ü��
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select NVL(count(*),0) cnt from comments where contents_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contents_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
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
	public ArrayList<CommentsVo> comList(int contents_num ,int startRow,int endRow) {
		Connection con=null; //�������¡ó���� ���� ���������� �������� �ش��ϴ� �� ���
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * " + 
					"from(select aa.*, rownum rnum " + 
					"from (select * from comments where contents_num=? order by comments_ref desc,comments_step asc)aa" + 
					")" + 
					" where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contents_num);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			ArrayList<CommentsVo> comList=new ArrayList<CommentsVo>();
			while(rs.next()) {
				CommentsVo vo=new CommentsVo(
					rs.getInt("comments_num"),
					rs.getInt("contents_num"),
					rs.getInt("users_num"),
					rs.getString("comments_content"),
					rs.getInt("comments_ref"),
					rs.getInt("comments_lev"),
					rs.getInt("comments_step"));
				comList.add(vo);
			}
			return comList;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}
	}
	public int maxNum() {
		Connection con=null; //댓글페이징처리를 위해 페이지마다 페이지에 해당하는 행 출력
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select NVL(max(comments_num),0) max from comments";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("max");
			}
			return -1;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}
	}
	
	public int insert(CommentsVo vo) { //댓글입력
		Connection con=null;  
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		try {
			con=ConnectionPool.getCon();
			int comments_num=vo.getComments_num();
			int comments_ref=maxNum()+1;
			int comments_lev=0;
			int comments_step=0;
			if(comments_num>0) {
				comments_ref=vo.getComments_ref();
				comments_lev=vo.getComments_lev();
				comments_step=vo.getComments_step();
				String sql1="update comments set comments_step=comments_step+1 "
						+ "where comments_ref=? and comments_step>?";
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, comments_ref);
				pstmt1.setInt(2, comments_step);
				pstmt1.executeUpdate();
				comments_step++;
				comments_lev++;
			}
			String sql="insert into comments values(comments_seq.nextval,?,?,?,?,?,?) ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getContents_num());
			pstmt.setInt(2, vo.getUsers_num());
			pstmt.setString(3, vo.getComments_content());
			pstmt.setInt(4, comments_lev);
			pstmt.setInt(5, comments_ref);
			pstmt.setInt(6, comments_step);
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
	
	public int update(String comments_content, int comments_num) { 
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="update comments set comments_content=? where comments_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, comments_content);
			pstmt.setInt(2, comments_num);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.getStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}
	}
	public int update_point(int users_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="update users_cafe set users_cafe_point=users_cafe_point+10 where users_num=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, users_num);
			return pstmt.executeUpdate();		
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();	
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public int delete_comments(int comments_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="update comments set comments_content='삭제된 댓글입니다.' where comments_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, comments_num);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}
		
	}
	
	
	public UserVo getUserId(int users_num) {
		String sql="select users_id from users where users_num=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, users_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				UserVo vo=new UserVo(
						users_num,
						rs.getString("users_id"));
				return vo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}
	}
}
