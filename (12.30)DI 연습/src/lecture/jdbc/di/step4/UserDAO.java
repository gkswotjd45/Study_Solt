package lecture.jdbc.di.step4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// 이거 상속도 안되고, 그렇다고 DAO안에 데이터베이스 연결정보를 넣자니.. 말이 안됨
// 연결정보를 클래스 단위로 분리하면 ? 이 클래스안에만 들어지 않으면 되니까 클래스에 내용에 빼냄.

public class UserDAO { // 팔아 먹으려는 클래스 임.
	
	private SimpleConnectionMakeup simpleConnectionMakeup; // 다른 객체 래퍼런스 함.
	// 따라서 사간사람은 해당 클래스도 필요함 => 클래스가 의존 형태임.
	// 타 사용자들은 Simple 객체를 생성하고 있어야함 :"tighy 형태" = denpendency (의존) "simple 클래스에게 의존한 형태"
	// 클래스가 종속되어 있어 한 묶음 형태 => 따라서 재활용 형태로 사용할 수 없음 
	
	public UserDAO() {
		simpleConnectionMakeup = new SimpleConnectionMakeup(); 
	}
	
	public void insert(User user) { // 각각의 컴포넌트들 VO을 통해서 주고 받음.
		// 일반 jdbc 코드가 나옴.
		try {
			Connection con  = simpleConnectionMakeup.getConnection(); // 위에서 만든 메서드를 통해 커낵션을 반환.
			
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
			
			Connection con  = simpleConnectionMakeup.getConnection(); 
			
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
