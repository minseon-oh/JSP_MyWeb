package com.myweb.user.model;

import java.sql.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.util.JdbcUtil;

public class UserDAO {
	
	private static UserDAO instance = new UserDAO();

	private UserDAO() {
		
		try {
			
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/oracle");
			
		} catch (Exception e) {
			System.out.println("클래스 로딩 중 에러발생");
		}
	}
	
	public static UserDAO getInstance() {
		return instance;
	}
	
	//private String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	//private String uid = "JSP";
	//private String upw = "JSP";
	
	private DataSource ds;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//아이디 중복검사
	public int checkId(String id) {
		int result = 0;
		
		String sql = "select * from users where id = ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			}else {
				result = 0;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		return result;
	}
	
	//회원가입처리
	public int insert(UserVO vo) {
		int result = 0;
		
		String sql = "insert into users(id, pw, name, email, address) values(?,?,?,?,?)";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
		return result;
	}
	
	//로그인 유효성 검사
	public int login(String id, String pw) {
		int result = 0;
		
		String sql = "select * from users where id = ? and pw = ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			}else {
				result = 0;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		return result;
	}
	
	//회원정보를 가려오는 메서드
	public UserVO getInfo(String id) {
		UserVO vo = new UserVO();
		
		String sql = "select * from users where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			//pw를 제외하고 rs에 있는 회원정보를 vo에 담는 코드
			if(rs.next()) {
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setAddress(rs.getString("address"));
				vo.setRegdate(rs.getTimestamp("regdate"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	//회원정보수정
	public int update(UserVO vo) {
		
		int result = 0;
		
		String sql = "update users set pw = ?, name = ?, email = ?, address = ? where id = ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getId());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		return result;
	}
	
	public int delete(String id) {
		int result = 0;
		
		String sql = "delete from users where id = ?";
				
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
		return result;
	}

}
