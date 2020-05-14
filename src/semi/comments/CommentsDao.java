package semi.comments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.ConnectionPool;

public class CommentsDao {
	private static CommentsDao instance=new CommentsDao();
	private CommentsDao() {}
	public static CommentsDao getInstance() {
		return instance;
	}
	public int getCount(int contents_num) { // 해당게시글의 댓글 전체행
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
		Connection con=null; //댓글페이징처리를 위해 페이지마다 페이지에 해당하는 행 출력
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
	public int insert(int contents_num,String comments_content,int users_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="insert into comments values(comments_seq.nextval,?,?,?,0,comments_seq.currval,1) ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contents_num);
			pstmt.setInt(2, users_num);
			pstmt.setString(3, comments_content);
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
	
	public int modifyCom(String comments_content, int comments_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="update comments set comments_content=? where comments_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, comments_content);
			pstmt.setInt(2, comments_num);
			int n=pstmt.executeUpdate();
			return n;
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
	public int deleteCom(int comments_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="delete from comments where comments_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, comments_num);
			int n=pstmt.executeUpdate();
			return n;
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
