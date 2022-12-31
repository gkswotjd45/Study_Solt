package lecture.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import lecture.jdbc.dao.BookDAO;
import lecture.jdbc.dao.DBCPConnectionPool;
import lecture.jdbc.vo.BookVO;

//책에 대한 비즈니스 메소드(로직을)를 처리하는 클래스
public class BookService {
	//메서드를 만든 형태.
	public ObservableList<BookVO> selectBooksByKeyword(String text) { // 서비스는 1개는 독립적인 트랜잭션
		// 검색 키워드를 받아서 observablelist.<bookVo>를 리턴하는 하나의 형태.
		// 작업(트랜잭션)을 처리
		// 이적 작업을 하기 위해서, 로직처리를가 필요하고 (더하고 빼고, for, if)
		// 당연히 데이터베이스 처리가 필요.
		
		// 그런데 우리 문제는 워낙 단순해 (간단해) 그냥 단순히 데이터 베이스 테이블 뒤져서 결과값을 가져오면 끝남.
		// 자금 데이터 베이스 처리를 해봅시다.
		// 그럼 누구한테 시킴? -> DAO (data Acess objec)한테 시킴.
		/*
		BookDAO dao = new BookDAO();
		ObservableList<BookVO> list = dao.select(text);// 데이터 베이스를 전문적으로 처리 삽입 삭제 수정 CRUD 작업 수행
		// dao 역핳은 데이터베이스를 전문적으로 수행 => busincess 로직을 작업하면 안됨.
		return list;*/
		Connection con  = null;
		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
			con.setAutoCommit(false);
			// 커넥션 풀에서 getconnection 커낵션 객체를 가져옴.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookDAO dao = new BookDAO(con);
		ObservableList<BookVO> list = dao.select(text);
		
		return list;
		
	}

	public ObservableList<BookVO> deleteByISBN(String deleteISBN, String searchKeyword) {
		// 여기서 부터 트랜잭션 시작이에요.
		// 여기서 커낵션에 대해서 SetAutocmmit()를 false로 지정해야 Transaction 시작!
		
		// 여기서 데이터 베이스 커밋션을 얻어와야 함. => 트랜잭션 처리할려고
		Connection con  = null;
		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
			con.setAutoCommit(false);
			// 커넥션 풀에서 getconnection 커낵션 객체를 가져옴.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BookDAO dao = new BookDAO(con);
		
		// 아래 2개의 코드는 개별적으로 트랜잭션이 발생. => 하나의 트랜잭션이 수행되도록 변경. -> DI
		int count = dao.delete(deleteISBN);
		ObservableList<BookVO> list = dao.select(searchKeyword);
		
		
		// 여기가 트랜잭션 끝임.
		// 여기서 서비스 메서드 맨 마지막에 이 메서드 잘 처리 되었으면, 트랜잭션을 커밋해야하고, 
		// 그렇지 않으면, rollback 해야함.
		// 하나의 커넥션을 이용해야히 commit이 유지가 됨. => 하나의 독립적인 트랜잭션을 유지 -> 하나의 dao에서 처리 해야힘.
		// 이 로직 코드는 당연히 우리가 처리해야 함.
		if(count  == 1 && list != null) {
			try {
				con.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				con.rollback(); //지금까지 작업했던 작업을 rollback
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
}
