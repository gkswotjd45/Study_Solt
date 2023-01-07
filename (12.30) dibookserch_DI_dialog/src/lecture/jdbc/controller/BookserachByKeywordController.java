package lecture.jdbc.controller;

import javafx.collections.ObservableList;
import lecture.jdbc.service.BookService;
import lecture.jdbc.vo.BookVO;

// 컨트롤러 객체. //클래스를 만드는 형태.
public class BookserachByKeywordController {

	public ObservableList<BookVO> getResult(String text) {
			// controller의 역할은 View와 "서비스" _로직 처리 (service model)간의 연결.
			// 서비스 객체가 있어야지 일을 수행하 겠죠.
			BookService service = new BookService();
			ObservableList<BookVO> list = 
					service.selectBooksByKeyword(text); // 이 메세드 이 뷰가 처리하려는 트랜잭션이어여 함.
			// 트랜잭션 - 일의 최소단위. -> 아름자체가 트랜잭션 그 자체에응 대표하는 의미.
		return list;
	}
	
}
