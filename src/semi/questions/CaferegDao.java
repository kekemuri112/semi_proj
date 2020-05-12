package semi.questions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import semi.db.ConnectionPool;

public class CaferegDao {
	public static CaferegDao instance=new CaferegDao();
	private CaferegDao() {}
	public static CaferegDao getCaferegDao() {
		return instance;
	}
	
	public int insert(int cafe_num,String cafereg_question) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="insert into cafereg values(cafereg_seq.nextval,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cafe_num);
			pstmt.setString(2, cafereg_question);
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
