package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class Main {
	public static void main(String[] args) {
		try {
			// 1. jdbc driver 로딩 단계 
			Class.forName("com.mysql.cj.jdbc.Driver"); // 클래스 이름을 통해 해당 클래스 찾기. // 클래스를 가져올때 패키지 명도 같이 가져와야 함
			System.out.println("Driver loading 성공!"); // 해당 클래스를 불러와서 성공했다는 의미.
			String jdbc_url 
			= "jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
					// 젒속할 수 있는 문자열. 프로토콜,사용 db, ip, port번호 실질 데이터베이스 명, mysql 접속 정보 추가
			String id = "root";
			String pw = "jaenir3529";
			// 2.데이터 베이스 접속.
			Connection con = DriverManager.getConnection(jdbc_url,id,pw); // 데이터 베이스를 연결하라는 메서드 
			// static 메서드 (클래스이름. 메서드명 바로 호출) // Connection 객체를 만들어짐 (객체와 연결하면 생성되는 객체)
			System.out.println("Database 접속 성공!");
			
			/*
			//일반 statment 사용
			// 3.statement 생성
			Statement stmt = con.createStatement(); // java.sql.statement 클래스인 statement 사용. // 집 마차 생성.
			
			// 4. Query를 작생해서 실행시킴. (select문)
			String keyword = "자바";
			String sql = "select bisbn,btitle,bauthor, bprice from book";
			ResultSet rs = stmt.executeQuery(sql);//select 문 계열 exceuteQuery 수행.
			
			// 5. 결과처리// resultset 커서의 의미.
			/*
			rs.next();
			String titile = rs.getString("btitle"); //문자열을 갖고옴 (컬럼명)
			// String titile = rs.getString(3); // 컬럼명, 인덱스도 가능은 하지만, 대부분은 컬럼명 사용.
			Integer num  = rs.getInt("bprice");
			String num1= rs.getString("btitle"); // String으로 가져올 수 있지만 가능하면 타입을 맞쳐서 사용.
			
			System.out.println("책 제목은 : " + titile);
			System.out.println("책 가격은 : " + num);
			*/
			/*
			while(rs.next()) { // 전체 책제목을 출력 rs.next() 항목이 있으면 true 없으면 false;
				String titile = rs.getString("btitle");
				System.out.println("책 제목은 : " + titile);
			}
			
			sql = "select bisbn,btitle,bauthor, bprice from book where btitle like '% " +keyword + "%'";
			rs = stmt.executeQuery(sql);
			
			*/
			// preparedstatement를 사용.
			// preparedstaement을 먼저 sql을 가지고 생성.
			// preparedStatement는 in parameter를 이용할 수 있음 -> ?로 표현.
			// 주의해야할 는 점은 keyword 부분에는 ?(in 파라마이터) 를 쓸수 없어요.
			String keyword = "자바";
			String sql = "select bisbn,btitle,bauthor, bprice from book where btitle like ?"; // ? 의미는 아직 그 값을 채우지 않음 // sql문이 완성되지 않음, 나중에 채워야할 항목을 ?대치
			PreparedStatement pstmt = con.prepareStatement(sql);
			// 실행하기 전에.. 를 채워야 해요.
			pstmt.setString(1,  "%" + keyword + "%"); // 첫번째 물음표에 들어갈 항목.
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) { // 전체 책제목을 출력 rs.next() 항목이 있으면 true 없으면 false;
				String titile = rs.getString("btitle");
				System.out.println("책 제목은 : " + titile);
				
			}
			
			// 6. 사용한 자원 해제 
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
