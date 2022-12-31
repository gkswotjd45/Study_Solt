package lecture.jdbc.di.step5;

import java.sql.Connection;
// 타 사용자에게 공유하는 인터페이스 클래스를 생성하여, 추상메서드 수행.
public interface ConnectionMakeup {
	public abstract Connection getConnection(); 
}
