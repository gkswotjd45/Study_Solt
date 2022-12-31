package lecture.jdbc.di.step2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {
	// 회원 가입하고 회원 정보를 추출하는 메서드 수행
		private Connection getConnection() { // 이 메서드를 호출하면 커낵션을 반환.
			
			Connection con = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String jdbc_url = "jdbc:mysql://127.0.0.1:3306/example01?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
				String id = "root";
				String password = "jaenir3529";
				con = DriverManager.getConnection(jdbc_url,id,password);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			return con;
		}
		//내부에서만 사용할 수 있는 메서드
	
	
		public void insert(User user) { // 각각의 컴포넌트들 VO을 통해서 주고 받음.
			// 일반 jdbc 코드가 나옴.
			try {
				Connection con  =getConnection(); // 위에서 만든 메서드를 통해 커낵션을 반환.
				
				String sql = "insert into users VALUES (?,?,?)"; // prefaredstetemnet 를 통해 나중에 값을 채움.
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, user.getId()); // 물음표 매칭.
				pstmt.setString(2, user.getName()); // 물음표 매칭.
				pstmt.setString(3, user.getPassword()); // 물음표 매칭.
				
				pstmt.executeUpdate(); // sql문 실행. // 사용자 등록.
				
				pstmt.close();
				con.close();
				
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		public User select(String string) {
			User user = null;
			
			try {
				
				Connection con = getConnection();
				
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
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return user;
		}
}
