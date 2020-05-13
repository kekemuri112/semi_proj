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
	public int getCommentsMaxNum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select NVL(max(comments_num),0) comments_maxnum from comments";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("comments_maxnum");
			}else {
				return -1;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
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
	public ArrayList<CommentsVo> comList(int contents_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			pstmt=con.prepareStatement("select *"+
					" from comments c, users u" + 
					" where c.users_num=u.users_num and contents_num=?");
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
	public int insertCom(CommentsVo vo) {
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		try {
			con=ConnectionPool.getCon();
			int comments_listnum=getCommentsMaxNum()+1; //등록하는 댓글 번호
			int contents_num=vo.getContents_num();
			int comments_ref=vo.getComments_ref();
			int comments_lev=vo.getComments_lev();
			int comments_step=vo.getComments_step();
			if(contents_num==0) { //새댓글인 경우
				comments_ref=comments_listnum;
			}else { //대댓리플인 경우
				String sql1="update into comments set comments_step=comments_step+1 where comments_ref=? and comments_step>?";
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, comments_ref);
				pstmt1.setInt(2, comments_step);
				pstmt1.executeUpdate();
				comments_lev += 1;
				comments_step += 1;
			}
			String sql2="insert into comments values(?,?,?,?,?,?,?)";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, comments_listnum);
			pstmt2.setInt(2, contents_num);
			pstmt2.setInt(3, vo.getUsers_num());
			pstmt2.setString(4, vo.getComments_content());
			pstmt2.setInt(5, vo.getComments_lev());
			pstmt2.setInt(6, vo.getComments_ref());
			pstmt2.setInt(7, vo.getComments_step());
			pstmt2.executeUpdate();
			return 1;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt1!=null) pstmt1.close();
				if(pstmt2!=null) pstmt2.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				s.printStackTrace();
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
		String sql="select user_id from users where users_num=?";
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
						rs.getInt("users_num"),
						rs.getString("users_id"));
				return vo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}
	}
}
