package semi.reg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import semi.db.ConnectionPool;


public class UsersDao {
	static UsersDao instance=new UsersDao();
	private UsersDao() {}
	
	public static UsersDao getInstance() {
		return instance;
	}
	// 아이디 검색하는 메소드
	public int idcheck(String users_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=ConnectionPool.getCon();
			String sql="select * from users where users_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, users_id);
			return pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
			return 0;
		}finally{
			try{
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s){
				s.printStackTrace();
			}
		}
	}
	public int emailCheck(String users_email) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from users where users_email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, users_email);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return 0;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}
		
	}
	
	// 유저 회원가입
	public int insert(UsersVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="insert into users values(users_seq.nextval,?,?,?,?,to_date(?,'yyyymmdd'),?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getUsers_id());
			pstmt.setString(2, vo.getUsers_pwd());
			pstmt.setString(3, vo.getUsers_name());
			pstmt.setString(4, vo.getUsers_email());
			pstmt.setString(5, vo.getUsers_birth());
			pstmt.setString(6, vo.getUsers_phone());
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
	//로그인 확인
	public int loginOk(String users_id,String users_pwd) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from users where users_id=? and users_pwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, users_id);
			pstmt.setString(2, users_pwd);
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
	//아이디 찾기
	public String search(String users_id,String users_name,String users_email) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from users where users_name=? and users_email=? ";
		try {
			con=ConnectionPool.getCon();
			if(users_id!=null) {
				sql+=" and users_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, users_name);
				pstmt.setString(2, users_email);
				pstmt.setString(3, users_id);
				rs=pstmt.executeQuery();
				rs.next();
				return rs.getString("users_pwd");
			}else {
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, users_name);
				pstmt.setString(2, users_email);
				rs=pstmt.executeQuery();
				rs.next();
				return rs.getString("users_id");
			}
		}catch(SQLException se) {
			se.printStackTrace();
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
	//유저아이디 찾아오기
	public UsersVo information(String users_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		UsersVo vo=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from users where users_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, users_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new UsersVo(
						rs.getInt("users_num"),
						users_id,
						rs.getString("users_pwd"),
						rs.getString("users_name"),
						rs.getString("users_email"),
						rs.getString("users_birth"),
						rs.getString("users_phone")
					);
			}
			return vo;
		}catch(SQLException se) {
			se.printStackTrace();
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
	//회원정보 수정
		public int update(UsersVo vo) {
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql="update users set users_pwd=?, users_email=?, users_phone=? where users_num=?";
			int n=0;
			try {
				con=ConnectionPool.getCon();
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, vo.getUsers_pwd());
				pstmt.setString(2, vo.getUsers_email());
				pstmt.setString(3, vo.getUsers_phone());
				pstmt.setInt(4, vo.getUsers_num());
				n=pstmt.executeUpdate();
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

}
