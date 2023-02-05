package system.book.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//이게 뭐하자는 건지 이해.
public class MyBatiesConnectionFactory {
	// 공장을 여러개 만드는 것은 사실상 필요 없음.
	private static SqlSessionFactory sqlsessionFactory;
	
	static {
		String resource = "./SqlMapConfig.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			if(sqlsessionFactory == null) { // 만약 공장이 없으면 - > 만듬 
				sqlsessionFactory= new SqlSessionFactoryBuilder().build(reader); //팩토리 빌더를 만들어서, (위에서 전달해주는 mapConfig.xml)
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlsessionFactory;
	}
}
