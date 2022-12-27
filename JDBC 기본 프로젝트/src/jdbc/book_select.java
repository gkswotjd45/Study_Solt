package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class book_select {
	public static void main(String[] args) {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver"); // 클래스 이름을 통해 해당 클래스 찾기. // 클래스를 가져올때 패키지 명도 같이 가져와야 함
			System.out.println("Driver loading 성공!"); // 해당 클래스를 불러와서 성공했다는 의미.
			String jdbc_url 
			= "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
					// 젒속할 수 있는 문자열. 프로토콜,사용 db, ip, port번호 실질 데이터베이스 명, mysql 접속 정보 추가
			String id = "root";
			String pw = "jaenir3529";
			
			Connection con = DriverManager.getConnection(jdbc_url,id,pw);
			System.out.println("database 접속 성공");
			
			Scanner sc = new Scanner(System.in);
			System.out.print("책 제목에 찾을 키워드: ");
			String keyword = sc.next();
			
			String sql = "select bisbn, btitle from book where btitle like ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword +"%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String title = rs.getString("btitle");
				String isbn = rs.getString("bisbn");
				
				System.out.println(isbn);
				System.out.println(title);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
