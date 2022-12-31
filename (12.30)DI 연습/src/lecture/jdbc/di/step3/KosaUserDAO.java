package lecture.jdbc.di.step3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// 상속형태로 제공하여 추상클래스의 추상메서드의 형태로 제공하여 상대방의 데이텁베이스 정보를에 맞춰서 오버라이딩으로 재 작성.
// 상속 = 클래스를 재활용하는 방안.
public class KosaUserDAO extends UserDAO{
// 만약, kosaUserDAO가 다른 클래스를 또 상속해야 한다면, -> 다중상속을 할수 없음. java는 단일 상속만 지원히가 때문에 이미 다른 클래스를 상속하고 있다면,
// 우리의 userDAO을 상속할 수 없음.
// 그러면 이문제는 어떻게 해결해야 함? -> 상속을 사용하지 않고 노력.
	
	@Override
	protected Connection getConnection() { // 자기 나름대로의 데이터 베이스 정보의 준비 코드를 넣어주면 커녁션을 리턴하는 코드를 작성하면 됨.
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
