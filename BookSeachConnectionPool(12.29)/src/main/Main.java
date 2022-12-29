package main;
// 이렇게 사용하는 용도로 만듬.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
	private static BasicDataSource basicDS; // 아파치에서 제공하는 클래스에 필드를 지정. // 커넉센 풀임.
	// 앞으로 데이터 베이스를 사용하는데 커넥션 풀을 통해서 수행 예정.
	//main 호출되기 전에 특정 코드를 실행시키고 싶음.
	//일반적으로 프로그램을 사용하는 resource 같은 거 로딩할 때 사용.
	static {
		//Connection pool을 만들 꺼임.
		basicDS = new BasicDataSource(); // new Connection pool 자체를 
		basicDS.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDS.setUrl("jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
		basicDS.setUsername("root");
		basicDS.setPassword("jaenir3529");
		basicDS.setInitialSize(10); // pool 사이즈의 초기 크기. 데이터베이스 커넥션연결을 초기에 10개 만듬.
		basicDS.setMaxTotal(20); // 전체 커넥션 몇 개로 할지.
		
	}
	//Data
	public static DataSource getDataSource() { // dataSource는 상위 타입으로 is-a관계성립 BaiscDataSoucre
		return basicDS;  // 이 메서드는 커녁션 풀을 얻을 수 있음 (외부 클래스가 접근하지 X static 으로 지정되어있게 때문에) 
	}
	
	public static void main(String[] args) {
		// DBCP사용에 대해서 알아봄.
		// 만들고 사용하는 과정이 살짝 알아봄.

		DataSource ds = getDataSource(); // 커넥션 풀을 가져와서 상위 타입의 클래스에 담음. // 상위타입인 풀부터 가져와서 순차적으로 진행.
		Connection con = null; //지역 변수이므로 초기화 수행.
		
		try {
			con = ds.getConnection();  // 커녁션을 얻어오는 방법을 수정하여 진행.
			String sql = "Select btitle, bauthor From book";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("btitle"));
			}
			rs.close();
			pstmt.close();
			con.close(); // 연결작업을 끝는 작업을 하는게 아니라 , pool에 반납. ㄴ
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 커녁션 풀안에 커넥션이 있어 ds에 커녁션을 넣음.

		
		
		
	}
}
