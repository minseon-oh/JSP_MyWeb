package com.myweb.util.upload;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
	location = "D:\\course\\jsp\\upload",
	maxFileSize = -1, //-1은 제한이 없다는 뜻
	maxRequestSize = -1,
	fileSizeThreshold = 1024
)
@WebServlet("/MultiUploadServlet")
public class MultiUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MultiUploadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<String> list = new ArrayList<String>(); //파일이름을 추가할 리스트
		String realFileName = null;
		
		try {
			Collection<Part> parts = request.getParts();
			System.out.println(parts.toString());
			for(Part part : parts) {//파일데이터가 담겨있는 객체를 찾는작업
				
				System.out.println(part.getContentType());
				
				if(part.getHeader("Content-Disposition").contains("filename=")) {//파일업로드된 객체를 확인하는 코드
					
					realFileName = part.getSubmittedFileName(); //실제 업로드된 파일명
					
					if(part.getSize() > 0) {
						part.write("D:\\course\\jsp\\upload\\" + realFileName);
						part.delete();//임시로 업로드된 파일 삭제
					}
					
					list.add(realFileName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(list.toString());
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			
//			conn = DriverManager.getConnection(url, user, password);
//			
//			for(String fileName : list) {
//				pstmt = conn.prepareStatement("insert into upload (id, fileName) values(?,?)");
//				pstmt.setString(1, "kkk123");
//				pstmt.setString(1, fileName);
//				pstmt.executeUpdate();
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	}

}
