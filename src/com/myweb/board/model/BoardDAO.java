package com.myweb.board.model;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.util.JdbcUtil;

public class BoardDAO {
	
	private static BoardDAO instance = new BoardDAO();

	private BoardDAO() {
		
		try {
			
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/oracle");
			
		} catch (Exception e) {
			System.out.println("클래스 로딩 중 에러발생");
		}
	}
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	private DataSource ds;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//게시글 등록 메서드
	public void regist(String writer, String title, String content) {
		String sql = "insert into board(bno,writer,title,content) values(board_seq.nextval,?,?,?)";
		
		try {
			
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}
	
	//글 목록 조회메서드
	public ArrayList<BoardVO> getList(int pageNum, int amount) {
		
		ArrayList<BoardVO> list = new ArrayList<>();
		
		//DB에서 모든 글 목록을 조회해서 VO에 담고 VO를 list에 추가
		String sql = "select *\r\n" + 
				"from(\r\n" + 
				"    select rownum rn,\r\n" + 
				"            bno,\r\n" + 
				"            writer,\r\n" + 
				"            title,\r\n" + 
				"            content,\r\n" + 
				"            regdate,\r\n" + 
				"            hit\r\n" + 
				"    from(\r\n" + 
				"        select * from board order by bno desc\r\n" + 
				"        )\r\n" + 
				"    )\r\n" + 
				"where rn > ? and rn <= ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			//전달되는 페이지 번호를 받아서 쿼리문에 저장 
			pstmt.setInt(1, (pageNum - 1) * amount );
			pstmt.setInt(2, (pageNum * amount) );
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
				BoardVO vo = new BoardVO(rs.getInt("bno"), rs.getString("writer"),  rs.getString("title"),  rs.getString("content"), rs.getTimestamp("regdate"), rs.getInt("hit"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	//전체 게시글 수 구하는 메서드
	public int getTotal() {
		
		int total = 0;
		
		String sql = "select count(*) as total from board";
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt("total");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return total;
	}
	
	//게시글 상세보기 매서드
	public BoardVO getContent(String bno) {
		
		BoardVO vo = new BoardVO();
		
		String sql = "select * from board where bno = ?";
				
		try {
			
			conn = ds.getConnection();
			pstmt =  conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setBno(rs.getInt("bno"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				vo.setHit(rs.getInt("hit"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(conn, pstmt, rs);
		}

		return vo;
	}
	
	public BoardVO update(String bno, String title, String content) {
		BoardVO vo = new BoardVO();
		
		String sql = "update board set title = ?, content = ? where bno = ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt =  conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, bno);
			
			pstmt.executeQuery();

			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
		return vo;
	}
	
	public void delete(String bno) {
		
		String sql = "delete from board where bno = ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt =  conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			
			pstmt.executeQuery();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}
	
	//조회수 관련 메서드
	public void upHit(String bno) {
		
		String sql = "update board set hit = hit + 1 where bno = ?";
		
		try {
			
			conn = ds.getConnection();
			pstmt =  conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			
			pstmt.executeQuery();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}