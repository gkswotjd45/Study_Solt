package lecture.jdbc.di.step5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO  {
	
	private ConnectionMakeup connectionMakeup; // 해당 인터페이스를 필드로 사용.
	
	public UserDAO() {
		 connectionMakeup = new KosaMakeUp_buyer(); //결래스 대신 인터페이스 선언.
		 // 인터페이스는 객체를 만드는 코드가 반드시 필요함. => 사간사람을 객체 코드가 또나옴,
		 // why? 인터페이스는 객체를 만들어야지 connection 객체를 사용할 수 있음 = > 다시 강결합.
		 // 따라서 해당 의존관계를 다시 작성.
		 
	}
	public void insert(User user) { // 각각의 컴포넌트들 VO을 통해서 주고 받음.
		// 일반 jdbc 코드가 나옴.
		try {
			Connection con  = connectionMakeup.getConnection(); // 위에서 만든 메서드를 통해 커낵션을 반환.
			
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
			
			Connection con  = connectionMakeup.getConnection(); 
			
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
