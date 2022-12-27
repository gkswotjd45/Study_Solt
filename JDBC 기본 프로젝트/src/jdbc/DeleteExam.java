package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteExam {
	
	public static void main(String[] args) {
		//특정한 채을 지움.
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbc_url 
			= "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "jaenir3529";
			
			//2. 데이터 베이스 연결.
			Connection con = DriverManager.getConnection(jdbc_url,id,pw);
			con.setAutoCommit(false); // 트랜잭션 시작. 그 때부터 해당 문장 이후 트랜잭션 걸림 수행.
			
			//3. preparedStatment 생성.
			String sql = "Delete From book where btitle like ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%여행%"); // 첫번 째 물음표에 삽입.
			
			//4. 실행
			int count = pstmt.executeUpdate(); // 실제로 실행하면 리턴 값은 정수값이 나옴.
			//영향을 받은 row의 수.
			
			//5. 결과 처리.
			System.out.println("삭제한 row의 수는 :" +count );
			// con.commit(); // 트랜잭션을 종료하고, 지금 까지 실행한 sql문을 실제로 적용.
			con.rollback();  // 트랜잭션을 종료하고, 지끔까지 한 sql문 무효화 시킴.
			// 만약 트랜잭션을 종료하지 않고, 커넥션을 close 하면, 이때 트랙잭션을 종료하고 자동으로 커밋시킴.(적용 시킴)
			
			//6. 사용한 자원 반납.
			pstmt.close();
			con.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
