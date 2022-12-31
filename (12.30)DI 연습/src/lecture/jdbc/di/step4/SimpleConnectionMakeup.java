package lecture.jdbc.di.step4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 별도의 클래스 여기에 "연결정보를 가지고 커녁션을 생성하는 메서드"를 이 클래스 안에 작성하자는 이야기.
public class SimpleConnectionMakeup {

	public Connection getConnection() { // 이 메서드를 호출하면 커낵션을 반환.
		// 이메소드는 다른 클래스에서 사용하기에 public 지정.
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
}
