package semi.questions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.ConnectionPool;

public class CaferegDao {
	public static CaferegDao instance=new CaferegDao();
	private CaferegDao() {}
	public static CaferegDao getInstance() {
		return instance;
	}
	
	public int insert(int cafe_num,String cafereg_question) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			System.out.println("cafe_num:"+cafe_num+",cafereg_question:"+cafereg_question);
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
	
	public ArrayList<CaferegVo> getQuestions(int cafe_num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select cafereg_question, cafereg_num from cafereg where cafe_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cafe_num);
			rs=pstmt.executeQuery();
			ArrayList<CaferegVo> list=new ArrayList<CaferegVo>();
			while(rs.next()) {
				CaferegVo vo=new CaferegVo();
				vo.setCafereg_num(rs.getInt("cafereg_num"));
				vo.setCafereg_question(rs.getString("cafereg_question"));
				list.add(vo);
			}
			return list;
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
	//질의응답, 목록 인서트
	public int usersCafeInsert(String[] contents, String[] scafereg_num,int cafe_num,int users_num) {
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		try {
			con=ConnectionPool.getCon();
			//카페가입목록 인설트
			String sql1="insert into users_cafe values(users_cafe_seq.nextval,?,?,0,'대기')";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, cafe_num);
			pstmt1.setInt(2, users_num);
			int n=pstmt1.executeUpdate();
			if(contents!=null) {
				for(int i=0;i<contents.length;i++) {
					String aContent=contents[i];
					String acafereg_num=scafereg_num[i];
					String sql2="insert into answer values(?,users_cafe_seq.currval,?)";
					pstmt2=con.prepareStatement(sql2);
					pstmt2.setString(1, acafereg_num);
					pstmt2.setString(2, aContent);
					System.out.println(i+"번째aContent:"+aContent);
					System.out.println(i+"번쨰acafereg_num:"+acafereg_num);
					n += pstmt2.executeUpdate();
				}
			}
			return n;
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
}
