package lecture.jdbc.di.step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 사용자와 관련된 데이터 베이스 처리를 의미 // 각각의 카테고리 (사람, 상품) 등을 별도로 만들어 줌, => 따라서 Dao가 하나만 있는 것이 아님.
// dao는 단순히 데이터베이스 처리만 담당.
// DAO는 Busincess logic이 나올 수 없어요. => Service 객체가 담당. => 단위 데이터 베이스 처리만 담당.
// ex) public selectuserKeyword() 형태가 나올 수 없음. 

public class UserDAO {
	
	// 회원 가입하고 회원 정보를 추출하는 메서드 수행
	
	public void insert(User user) { // 각각의 컴포넌트들 VO을 통해서 주고 받음.
		// 일반 jdbc 코드가 나옴.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/example01?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String password = "jaenir3529";
			
			Connection con = DriverManager.getConnection(jdbc_url,id,password);
			
			String sql = "insert into users VALUES (?,?,?)"; // prefaredstetemnet 를 통해 나중에 값을 채움.
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user.getId()); // 물음표 매칭.
			pstmt.setString(2, user.getName()); // 물음표 매칭.
			pstmt.setString(3, user.getPassword()); // 물음표 매칭.
			
			pstmt.executeUpdate(); // sql문 실행. // 사용자 등록.
			
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

	public User select(String string) {
		User user = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/example01?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String password = "jaenir3529";
			
			Connection con = DriverManager.getConnection(jdbc_url,id,password);
			
			String sql = "select * from users where id = ?"; // prefaredstetemnet 를 통해 나중에 값을 채움.
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, string); // 물음표 매칭.

			
			ResultSet rs = pstmt.executeQuery();// sql문 실행. // 사용자 검색.
			rs.next();
			
			user = new User(string,
					rs.getString("name"), 
					rs.getString("password"));
			
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
		
		return user;
	}
}
