package semi.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import semi.db.ConnectionPool;


public class NoticeDao {
	private static NoticeDao instance =new NoticeDao();
	private NoticeDao() {}
	public static NoticeDao getInstance() {
		return instance;
	}
	
	public int maxRef() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select max(notice_ref)+1 from notice";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
			
		}catch(SQLException se) {
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
	//새게시판 메소드(New ref)
	public int insert(NoticeVo vo) {
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		try {
			con=ConnectionPool.getCon();
			int cafe_num=vo.getCafe_num();
			String notice_name=vo.getNotice_name();
			int notice_ref=vo.getNotice_ref();
			int notice_lev=vo.getNotice_lev();
			int notice_step=vo.getNotice_step();
			int notice_grade=vo.getNotice_grade();
			
			//새끼게시판
			if(notice_lev==0) {
				String sql1="update notice set step=step+1 where ref=? and step>?";
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, notice_ref);
				pstmt1.setInt(2, notice_step);
				pstmt1.executeUpdate();
				notice_lev++;
				notice_step++;
			}else {
				String sql1="update notice set step=step+1 where ref=? and step>?";
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, notice_ref);
				pstmt1.setInt(2, notice_step);
				pstmt1.executeUpdate();
				notice_step++;
			}

			String sql2="insert into notice values(notice_seq.nextval,?,?,?,?,?,?)";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, cafe_num);
			pstmt2.setString(2, notice_name);
			pstmt2.setInt(3, notice_lev);
			pstmt2.setInt(4, notice_ref);
			pstmt2.setInt(5, notice_step);
			pstmt2.setInt(6, notice_grade);
			pstmt2.executeUpdate();
			return 1;
		}catch(SQLException se) {
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

	//새끼게시판 메소드(child ref)
	public int insert(String notice_name,int cafe_num,int notice_grade) {
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		try {
			con=ConnectionPool.getCon();
			//새글
			String sql2="insert into notice values(notice_seq.nextval,?,?,0,?,0,?)";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, cafe_num);
			pstmt2.setString(2, notice_name);
			pstmt2.setInt(3, maxRef());
			pstmt2.setInt(4, notice_grade);
			pstmt2.executeUpdate();
			return 1;
		}catch(SQLException se) {
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
	
	public ArrayList<NoticeVo> listAll(int cafe_num){
		System.out.println(cafe_num+"listAll메소드에 접근함.");
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from notice where cafe_num=? order by notice_ref desc,notice_step asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cafe_num);
			rs=pstmt.executeQuery();
			ArrayList<NoticeVo> list=new ArrayList<NoticeVo>();
			while(rs.next()) {
				System.out.println("rs.next() 실행중..");
				NoticeVo vo=new NoticeVo(
							rs.getInt("notice_num"),
							cafe_num,
							rs.getString("notice_name"),
							rs.getInt("notice_lev"),
							rs.getInt("notice_ref"),
							rs.getInt("notice_step"),
							rs.getInt("notice_grade")
						);
				list.add(vo);
			}
			System.out.println("리턴전..");
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			System.out.println("리턴불가 NULL ");
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
	public int getCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select NVL(count(num),0) cnt from notice";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0;
			}
			
		}catch (SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
			if(rs!=null)rs.close();
			if(pstmt!=null)rs.close();
			if(con!=null)rs.close();
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}
	}
	public int delete(NoticeVo vo,Contents Vo,) {
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		PreparedStatement pstmt4=null;
		PreparedStatement pstmt5=null;
		PreparedStatement pstmt6=null;
		try {
			con=ConnectionPool.getCon();

			//대댓글지우기(lev이 1인 자식게시판)
			String sql1="delete from comments where comments_num=? and comments_ref=?";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, comments_num);
			pstmt1.setInt(2, comments_ref);
			//댓글지우기(lev이 0인 부모게시판)
			String sql2="delete from (select contents_num from comments ref=?)";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, comments_ref);
			//게시물 지우기
			String sql3="delete from (select notice_num from contents where notice_num=?)";
			pstmt3=con.prepareStatement(sql3);
			pstmt3.setInt(1, notice_num);
			//작은게시판 지우기(lev이 0이상인 자식게시판)
			String sql4="DELETE FROM notice WHERE notice_num = ? and notice_ref = ?";
			pstmt4=con.prepareStatement(sql4);
			pstmt4.setInt(1, notice_num);
			pstmt4.setInt(2, notice_ref);
			//큰게시판 지우기(lev이 0인 부모게시판)
			String sql5="delete from notice where notice_num=?";
			pstmt5=con.prepareStatement(sql5);
			pstmt5.setInt(1, notice_num);
			
/*String sql2="DELETE FROM board WHERE num in (SELECT num FROM START WITH num = 글번호"
			+ "  CONNECT BY PRIOR  num = parent_id)";
*/
//String sql3="DELETE FROM `테이블명` WHERE `wr_parent` = 글번호 and `wr_is_comment` = 1";					
/*
START WITH num = 글번호 : 검색을 시작할 번호
CONNECT BY PRIOR num = parent_id :  검색 대상을 트리 형태로 검색

PRIOR 의 위치 
- CONNECT BY PRIOR 자식컬럼 =  부모컬럼  : 부모에서 자식으로 트리 구성
- CONNECT BY  자식컬럼 = PRIOR 부모컬럼  : 자식에서 부모으로 트리 구성
*/

			pstmt6=pstmt5;
			return pstmt6.executeUpdate();

		}catch (SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt1!=null)pstmt1.close();
				if(pstmt2!=null)pstmt2.close();
				if(pstmt3!=null)pstmt3.close();
				if(pstmt4!=null)pstmt4.close();
				if(pstmt5!=null)pstmt5.close();
				if(con!=null)con.close();
			}catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}
	
	//PK값 하나 받아서 모든값을 가져오는 메소드 (rs 를 사용)
	public NoticeVo getVo(int notice_num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from notice where notice_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			rs=pstmt.executeQuery();
			NoticeVo vo=null;
			if(rs.next()) {
				vo=new NoticeVo(
						notice_num,
						rs.getInt("cafe_num"),
						rs.getString("notice_name"),
						rs.getInt("notice_lev"),
						rs.getInt("notice_ref"),
						rs.getInt("notice_step"),
						rs.getInt("notice_grade")
					);
			}
			return vo;
		}catch (SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch (SQLException s) {
				s.printStackTrace();
			}
		}
	}
	
}
