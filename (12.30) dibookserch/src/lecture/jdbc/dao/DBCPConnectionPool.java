package lecture.jdbc.dao;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPConnectionPool {
private static BasicDataSource basicDS; // 커넥션 풀 작업
	
	// main이 호출되기 전에 특정 코드를 실행시키고 싶어요!
	// 일반적으로 프로그램에서 사용하는 resource같은거 로딩할때 사용해요!
	static {
		// Connection Pool을 만들꺼예요!
		basicDS = new BasicDataSource();
		basicDS.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDS.setUrl("jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
		basicDS.setUsername("root");
		basicDS.setPassword("jaenir3529");
		basicDS.setInitialSize(10);
		basicDS.setMaxTotal(20);		
	}
	
	public static DataSource getDataSource() {
		return basicDS;
	}
}
