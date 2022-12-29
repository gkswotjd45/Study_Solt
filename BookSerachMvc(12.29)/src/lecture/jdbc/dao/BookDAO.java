package lecture.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lecture.jdbc.vo.BookVO;

//데이터 베이스 전문처리 (객체) 만들기 위한 클래스.
public class BookDAO {

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
	
	public ObservableList<BookVO> select(String text) {

		DataSource ds = getDataSource();
		// 2. Database 접속
		Connection con = null;
		ObservableList<BookVO> list =null;
		try {
			con = ds.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bisbn, btitle, bauthor, bprice ");
		sql.append("FROM book ");
		sql.append("WHERE btitle like ?");
		sql.append("ORDER BY bprice DESC");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			// 실행하기 전에.. ? 를 채워야 해요!
			pstmt.setString(1, "%" + text + "%");
			
			ResultSet rs = pstmt.executeQuery();
			
			list = FXCollections.observableArrayList();
			
			// 5. 결과처리!
			while(rs.next()) {
				BookVO book = new BookVO(rs.getString("bisbn"),
						rs.getString("btitle"),
						rs.getString("bauthor"),
						rs.getInt("bprice"));
				list.add(book);
			}
			
		
			
			// 6. 사용한 리소스 반납
			rs.close();
			pstmt.close();
			con.close();

			
		} catch (Exception e1) {
			// TODO: handle exception
		}
		
		return list;
	}

	public int delete(String deleteISBN) {
		DataSource ds = getDataSource();
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append("FROM book ");
		sql.append("WHERE bisbn = ?");

		try {
			con.setAutoCommit(false);  // transaction 시작 
			
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			// 실행하기 전에.. ? 를 채워야 해요!
			pstmt.setString(1, deleteISBN);
			
			int count = pstmt.executeUpdate();
			
			// 5. 결과처리!
			if(count == 1) {
				con.commit();

			} else {
				con.rollback();
			}
			
			// 6. 사용한 자원 반납
			pstmt.close();
			con.close();
			
		} catch (Exception e1) {
			// TODO: handle exception
		}
		return 0;
	}
	
}
