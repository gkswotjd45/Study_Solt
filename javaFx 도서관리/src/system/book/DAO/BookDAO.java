package system.book.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import system.book.vo.BookVO;

public class BookDAO {
	private SqlSessionFactory factory;
	
	public BookDAO() {
		// TODO Auto-generated constructor stub
	}

	public BookDAO(SqlSessionFactory factory) {
		super();
		this.factory = factory;
	}

	public ArrayList<BookVO> bookSearch(String bookTitle) {
		List<BookVO> list = null;
		SqlSession session =factory.openSession();
		
		try {
			 list = session.selectList("example.mybook.selectBookTitle",bookTitle);
			 //System.out.println(list);
		}finally {
			
		}
		
		ArrayList<BookVO> alist = new ArrayList<BookVO>(list);
		return alist;
		
	}
}
