package semi.cafe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.ConnectionPool;

public class  UsersApprovalDao {
	private static UsersApprovalDao instance =new UsersApprovalDao();
	private UsersApprovalDao() {}
	public static UsersApprovalDao getInstance() {
		return instance;
	}	
	public ArrayList<UsersApprovalVo> getInfo(int cafe_num,int startRow,int endRow) { // 정보가져오기.
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select *" 
					  +" from"  
					  +" (select a.* ,rownum rnum " 
					  + " from"  
					  +" (select " 
					  + " u.users_num users_num,users_id,users_pwd,users_name,users_email,users_birth,users_phone,users_cafe_num,cafe_num,users_cafe_point,users_cafe_approved"  
					  +" from users u inner join users_cafe uc" 
					  +" on u.users_num=uc.users_num  where cafe_num=? order by users_cafe_approved asc users_cafe_point desc,users_cafe_num desc) a)"  
					  +" where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cafe_num);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			ArrayList<UsersApprovalVo>list =new ArrayList<UsersApprovalVo>();
			while(rs.next()) {
				UsersApprovalVo vo=new UsersApprovalVo(rs.getInt("users_num"), 
													   rs.getString("users_id"), 
													   rs.getString("users_pwd"), 
													   rs.getString("users_name"), 
													   rs.getString("users_email"), 
													   rs.getString("users_birth"), 
													   rs.getString("users_phone"), 
													   rs.getInt("users_cafe_num"), 
													   rs.getInt("cafe_num"), 
													   rs.getInt("users_cafe_point"), 
													   rs.getString("users_cafe_approved"));
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;	
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public int getCount(int cafe_Num) { //전체행 구하기 
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select NVL(count(*),0) cnt"
					+" from users u, users_cafe uc"
					+" where u.users_num=uc.users_num and cafe_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cafe_Num);
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
	public int denied(int users_cafe_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="update users_cafe set users_cafe_approved='거절' where users_cafe_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, users_cafe_num);
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
	public int accept(int users_cafe_num) { //대기 ->승인 기능
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="update users_cafe set users_cafe_approved='승인' where users_cafe_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, users_cafe_num);
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
	public int redCard(int users_cafe_num) {
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		try {
			con=ConnectionPool.getCon();
			con.setAutoCommit(false);
			String sql1="delete from answer where users_cafe_num=?";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, users_cafe_num);
			pstmt1.executeUpdate();
			
			String sql2="delete from users_cafe where users_cafe_num=?";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, users_cafe_num);
			int n=pstmt2.executeUpdate();
			if(n>0) {
				con.commit();
			}else {
				con.rollback();
			}
			return n;
			
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;	
		}finally {
			try {
				con.setAutoCommit(true);
				if(pstmt2!=null)pstmt2.close();
				if(pstmt1!=null)pstmt1.close();
				if(con!=null)con.close();
				
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public ArrayList<UsersAnswerVo> getAnswer(int users_cafe_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select cafereg_question,answer_contents,users_cafe_num,a.cafereg_num cafereg_num from (select * from answer) a,(select * from cafereg) c "
					  +"where a.cafereg_num=c.cafereg_num and users_cafe_num=? order by cafereg_num asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, users_cafe_num);
			rs=pstmt.executeQuery();
			ArrayList<UsersAnswerVo> list=new ArrayList<UsersAnswerVo>();
			while(rs.next()) {
				UsersAnswerVo vo=new UsersAnswerVo(rs.getInt("users_cafe_num"), rs.getInt("cafereg_num"), rs.getString("cafereg_question"), rs.getString("answer_contents"));
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
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	
}
