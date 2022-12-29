package lecture.jdbc.service;

import javafx.collections.ObservableList;
import lecture.jdbc.dao.BookDAO;
import lecture.jdbc.vo.BookVO;

//책에 대한 비즈니스 메소드(로직을)를 처리하는 클래스
public class BookService {
	//메서드를 만든 형태.
	public ObservableList<BookVO> selectBooksByKeyword(String text) {
		// 검색 키워드를 받아서 observablelist.<bookVo>를 리턴하는 하나의 형태.
		// 작업(트랜잭션)을 처리
		// 이적 작업을 하기 위해서, 로직처리를가 필요하고 (더하고 빼고, for, if)
		// 당연히 데이터베이스 처리가 필요.
		
		// 그런데 우리 문제는 워낙 단순해 (간단해) 그냥 단순히 데이터 베이스 테이블 뒤져서 결과값을 가져오면 끝남.
		// 자금 데이터 베이스 처리를 해봅시다.
		// 그럼 누구한테 시킴? -> DAO (data Acess objec)한테 시킴.
		
		BookDAO dao = new BookDAO();
		ObservableList<BookVO> list = dao.select(text);// 데이터 베이스를 전문적으로 처리 삽입 삭제 수정 CRUD 작업 수행
		// dao 역핳은 데이터베이스를 전문적으로 수행 => busincess 로직을 작업하면 안됨.
		return list;
	}

	public ObservableList<BookVO> deleteByISBN(String deleteISBN, String searchKeyword) {
		
		// 로직처리를 해야함.
		// DB처리만 있어요.
		BookDAO dao = new BookDAO();
		
		// 아래 2개의 코드는 개별적으로 트랜잭션이 발생. => 하나의 트랜잭션이 수행되도록 변경. -> DI
		int count = dao.delete(deleteISBN);
		ObservableList<BookVO> list = dao.select(searchKeyword);
		
		return list;
	}
	
	
}
