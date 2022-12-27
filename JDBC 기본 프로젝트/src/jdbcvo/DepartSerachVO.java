package jdbcvo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class DepartSerachVO {
	public static void main(String[] args) {
		
		try {
			// 1. jdbc를 이용해서 데이터베이스를 데이터를 추출.
			// 1. driver 로딩.
			Class.forName("com.mysql.cj.jdbc.Driver"); // driver 로딩. // try catch로 잡아줌 오류 방지.
			//2. 실제 데이터 베이스 연결. (실제 찾으려는 구문의 데이터베이스 명 변경.)
			String jdbc_url = "jdbc:mysql://127.0.0.1:3306/mysql_test_db?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
			String id = "root";
			String pw = "jaenir3529";
			// 커넥션 객체 연결 Driver manager를 통해 만든(주소,아이디 ,페스워드) 객체를 연결.
			Connection con = DriverManager.getConnection(jdbc_url,id,pw);
			
			
			// 3. 실행할 sql문장을 가지고 있는 statement를 생성. (prepared) 
			StringBuffer sql = new StringBuffer();
			sql.append("Select D.CATEGORY, D.DEPARTMENT_NAME, D.CAPACITY "); // sql문장 삽입시 마지막에 한칸 띄어 두기 안 그러면 오류 방생.
			sql.append("FROM tb_department D "); // 
			sql.append("WHERE D.CATEGORY = '공학' and D.CAPACITY between 20 and 30 ");
			sql.append("ORDER BY D.DEPARTMENT_NAME ASC");
			
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			
			//4. 실행.
			ResultSet rs = pstmt.executeQuery();
			// rs가 가르키는 항목은 실제로 칼럼명을 가르킴 (point) "제목줄"
			// 따라서 한번은 내려와야 함. 그래야 데이터가 저장된 항목에 접근 O
			
			//5. 결과 처리
			// rs. next() -> 데이터 항목을 가르킴.
			// 우리의 목표는 객체를 만들어서 (동서 의료공학과, 공학, 20)이 객체를 저장하는 것이 목적. => 이 형태를 VO
			// 따라서 일단 저장한 공간 필요 => 객체가 있어야함.
			// 객체를 만들려면, 1) 클래스 부터 생성 후 진행.
			// 클래스는 vo를 만들 꺼니까 vo 패키지명 만듬 => 사실 클래스는 테이블명과 동일하게 만드는게 관용.
			// vo 내가 원하는 형태로 만들 수 있기 때문에 임의로 필드를 선택후 제약. 
			
			/* rs. next(); 한 칸씩 내려서 객체를 1개 씩 생성.
			Department dep = new Department(rs.getString("D.CATEGORY"), 
					rs.getString("D.DEPARTMENT_NAME"), 
					rs.getInt("D.CAPACITY"));
			*/
			
			// 일반적으로 Arraylist로 저장시켜 하나씩 저장시킴. (Vo 형태를 저장)
			ArrayList<Department> list = new ArrayList<Department>();
			while(rs.next()) {
				// VO를 생성해야 함.
				Department dep = new Department(rs.getString("D.CATEGORY"), 
									rs.getString("D.DEPARTMENT_NAME"), 
									rs.getInt("D.CAPACITY"));
				//arraylist에 추가.
				list.add(dep);	
			}
			
			for(Department dep : list) {
				System.out.println(dep.getDepart_name()); // 이름만 호출 (getter 통해)
				System.out.println(dep);
			}
			
			
			//6. 사용한 resoure 해제.
			rs.close();
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
