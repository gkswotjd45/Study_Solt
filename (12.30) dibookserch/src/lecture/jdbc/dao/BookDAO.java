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
	Connection con; // 커넥션 객체를 검색 삭제 수정하면 커낵션 객체를 별도로 생성하지 않고, DAO에서 커낵션 풀을 생성하여,
	// 검색, 삭제, 수정 할때 커넥션 풀 객체를 가져와서 수행.
	public BookDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public BookDAO(Connection con) {
		super();
		this.con = con;
	}

	public ObservableList<BookVO> select(String text) {


		ObservableList<BookVO> list =null;
	
		
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
			
		} catch (Exception e1) {
			// TODO: handle exception
		}
		
		return list;
	}

	public int delete(String deleteISBN) {
	
		
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append("FROM book ");
		sql.append("WHERE bisbn = ?");
		int count = 0;
		try {
			
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			// 실행하기 전에.. ? 를 채워야 해요!
			pstmt.setString(1, deleteISBN);
			
			count = pstmt.executeUpdate();
			// 6. 사용한 자원 반납
			pstmt.close();

		} catch (Exception e1) {
			// TODO: handle exception
		}
		return count; // 삭제된 카운트 값을 반환.
	}
	
}
