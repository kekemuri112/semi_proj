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
	
	public CafeVo getVo(int cafe_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from cafe where cafe_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cafe_num);
			rs=pstmt.executeQuery();
			CafeVo vo=null;
			if(rs.next()) {
				vo=new CafeVo(
						rs.getInt("cafe_num"),
						rs.getString("cafe_name"),
						rs.getString("cafe_desc"),
						rs.getString("cafe_intent"),
						rs.getString("cafe_admin"),
						rs.getString("cafe_approved"),
						rs.getString("cafe_image")
					);
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
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}
	}
	
	
	public int cafeStatus(int cafe_num,boolean bl) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql=null;
			if(bl) {
				sql="update cafe set cafe_approved='승인' where cafe_num=?";

			}else {
				sql="update cafe set cafe_approved='거절' where cafe_num=?";
			}
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cafe_num);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}
	}
	
	//전체행 리턴
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select NVL(count(*),0) countNum from cafe where cafe_approved='대기'";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("countNum");
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
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}
	}
	
	//카페승인 목록 리스트.
	public ArrayList<CafeVo> approvalList(int startRow, int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from (select c.*,rownum rnum"
					+ " from (select * from cafe where cafe_approved='대기' order by cafe_num)c )"
					+ " where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
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
						null
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
	
	
	//리스트(카페이름)불러오는 메소드
	public ArrayList<CafeVo> listAll(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from cafe where cafe_approved='승인' order by cafe_name";
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
			String sql="insert into cafe (cafe_num,cafe_name,cafe_desc,cafe_intent,cafe_admin,cafe_approved,cafe_image)"
					+ " values(cafe_seq.nextval,?,?,?,?,'대기',?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getCafe_name());
			pstmt.setString(2, vo.getCafe_desc());
			pstmt.setString(3, vo.getCafe_intent());
			pstmt.setString(4, vo.getCafe_admin());
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
	public int usersdelete(int cafe_num,int users_num) {
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		try {
			con=ConnectionPool.getCon();
			String sql1="delete from users_cafe where cafe_num=? and users_num=?";
			String sql2="delete from answer where users_cafe_num in (select users_cafe_num from users_cafe where cafe_num=? and users_num=?)";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, cafe_num);
			pstmt1.setInt(2, users_num);
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, cafe_num);
			pstmt2.setInt(2, users_num);
			pstmt2.executeUpdate();
			return pstmt1.executeUpdate();
		}catch (SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt1!=null)pstmt1.close();
				if(pstmt2!=null)pstmt2.close();
				if(con!=null)con.close();
			}catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}
	
	
}
