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
	public ArrayList<CafeVo> listAll(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from cafe order by cafe_name desc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<CafeVo> list=new ArrayList<CafeVo>();
			while(rs.next()) {
				CafeVo vo=new CafeVo(
						rs.getInt("cafe_num"),
						rs.getString("cafe_name"),
						rs.getString("cafe_desc"),
						rs.getString("cafe_intent"),
						rs.getString("cafe_admin"),
						rs.getString("cafe_approved"),
						rs.getString("cafe_image")
					);
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
