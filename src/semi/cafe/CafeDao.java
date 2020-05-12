package semi.cafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.ConnectionPool;


public class CafeDao {
	private static CafeDao instance =new CafeDao();
	private CafeDao() {}
	public static CafeDao getInstance() {
		return instance;
	}
	//리스트(카페이름)불러오는 메소드
	public ArrayList<String> listAll(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select cafe_name from cafe order by cafe_name desc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<String> list=new ArrayList<String>();
			while(rs.next()) {
				System.out.println("rs.next()발동중..");
				list.add(rs.getString("cafe_name"));
			}
			System.out.println("리턴주기전이다.");
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			System.out.println("리턴안된거임.. NULL");
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
	//cafe추가하는 메소드
	public int insert(CafeVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="insert into cafe values(cafe_seq.nextval,?,?,?,?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getCafe_name());
			pstmt.setString(2, vo.getCafe_intent());
			pstmt.setString(3, vo.getCafe_admin());
			pstmt.setString(4, vo.getCafe_approved());
			pstmt.setString(5, vo.getCafe_image());
			return pstmt.executeUpdate();
		}catch (SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			}catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}
	//cafe삭제하는 메소드
	public int delete(int cafe_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="delete from cafe where cafe_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cafe_num);
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
