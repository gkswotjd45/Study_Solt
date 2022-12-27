package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookSearch {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		String input = s.nextLine();
		
		try {
			// 1. JDBC Driver Loading 단계
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "jaenir3529";
			// 2. Database 접속
			Connection con = DriverManager.getConnection(jdbc_url, id, pw); 
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT btitle, bauthor, bprice ");
			sql.append("FROM book ");
			sql.append("WHERE btitle like ?");
			sql.append("ORDER BY bprice DESC");

			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			// 실행하기 전에.. ? 를 채워야 해요!
			pstmt.setString(1, "%" + input + "%");
			
			ResultSet rs = pstmt.executeQuery();
			
			// 5. 결과처리!
			while(rs.next()) {
				String title = rs.getString("btitle");
				String author = rs.getString("bauthor");
				String price = rs.getString("bprice");
				System.out.println(title + ", " + author + ", " + price);				
			}
			
			// 6. 사용한 자원 해제.
			rs.close();
			pstmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
