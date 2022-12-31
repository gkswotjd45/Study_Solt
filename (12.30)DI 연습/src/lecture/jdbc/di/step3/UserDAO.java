package lecture.jdbc.di.step3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class UserDAO {
	
	protected abstract Connection getConnection(); // 추상메서드로 처리해야 클래스를 재활용 할 수 있음.
		// 패키지가 달라도 상속한 클래스면 사용할 수 있음. => 이 추상클래스를 사용하는 타사용자들은 다른 패키지 형태 이므로 protected
		// 
	

	public void insert(User user) { // 각각의 컴포넌트들 VO을 통해서 주고 받음.
		// 일반 jdbc 코드가 나옴.
		try {
			Connection con  = getConnection(); // 위에서 만든 메서드를 통해 커낵션을 반환.
			
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
