package com.myweb.util;

import java.sql.*;

public class JdbcUtil {
	
	//JDBC close작업을 메서드화
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		
		try {
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
