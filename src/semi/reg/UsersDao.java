package semi.reg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import semi.db.ConnectionPool;


public class UsersDao {
	static UsersDao instance=new UsersDao();
	private UsersDao() {}
	
	public static UsersDao getInstance() {
		return instance;
	}
	// 유저 회원가입
	public int insert(UsersVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="insert into users values(users_num.users_seq.nextval,?,?,?,?,?,?)";
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

}
